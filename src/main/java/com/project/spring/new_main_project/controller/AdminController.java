package com.project.spring.new_main_project.controller;


import com.project.spring.new_main_project.dto.ProductDTO;
import com.project.spring.new_main_project.dto.UserDTO;
import com.project.spring.new_main_project.model.Category;
import com.project.spring.new_main_project.model.Product;
import com.project.spring.new_main_project.model.Role;
import com.project.spring.new_main_project.model.User;
import com.project.spring.new_main_project.service.CategoryService;
import com.project.spring.new_main_project.service.ProductService;
import com.project.spring.new_main_project.service.RoleService;
import com.project.spring.new_main_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class AdminController {
    public static String uploadDirProduct = System.getProperty("user.dir") + "/src/main/resources/static/products";
    public static String uploadDirCategory = System.getProperty("user.dir") + "/src/main/resources/static/categories";

    private PasswordEncoder bCryptPasswordEncoder;
    private CategoryService categoryService;
    private ProductService productService;
    private UserService userService;
    private RoleService roleService;


    @Autowired // Autowired injection?
    public AdminController(PasswordEncoder passwordEncoder, CategoryService categoryService, ProductService productService, UserService userService, RoleService roleService){
        this.bCryptPasswordEncoder = passwordEncoder;
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping
    public String adminHome(Model model){
        return "adminPage";
    }//page admin home


    @GetMapping("/users")
    public String getUserAccounts(Model model){
        model.addAttribute("users", userService.getAllUser());
        //model.addAttribute("roles", roleService.getAllRole());
        return "user-edit-page";
    }
    @GetMapping("/users/add")
    public String fillNewUser(Model model){
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("roles",roleService.getAllRole());
        return "add-new-user";
    }
    @PostMapping("/users")
    public String addUser(@ModelAttribute("userDTO") UserDTO userDTO) {
        //convert dto > entity
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        List<Role> roles = new ArrayList<>();
        for (Integer item: userDTO.getRoleIds()) {
            roles.add(roleService.findRoleById(item).get());
        }
        user.setRoles(roles);

        userService.updateUser(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable int id){
        userService.removeUserById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/update/{id}")
    public String updateUser(@PathVariable int id, Model model){
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setPassword("");
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            List<Integer> roleIds = new ArrayList<>();
            for (Role item:user.getRoles()) {
                roleIds.add(item.getId());
            }

            model.addAttribute("userDTO", userDTO);
            model.addAttribute("roles", roleService.getAllRole());
            return "add-new-user";
        }else {
            return "404";
        }

    }


    @GetMapping("/categories")
    public String getCategory(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories-edit-page";
    }

    @GetMapping("/categories/add")
    public String fillNewCategory(Model model){
        model.addAttribute("categories", new Category());
        return "add-new-category";
    }

    @PostMapping("/categories")
    public String addCategory(@ModelAttribute("category") Category category,
                              @RequestParam("categories") MultipartFile fileCategoryImage,
                              @RequestParam("imgName") String imgName) throws IOException {
        String imageUUID;
        if(!fileCategoryImage.isEmpty()){
            imageUUID = fileCategoryImage.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDirCategory, imageUUID);
            Files.write(fileNameAndPath, fileCategoryImage.getBytes());
        } else {
            imageUUID = imgName;
        }
        category.setImageName(imageUUID);

        categoryService.updateCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id){
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories/update/{id}")
    public String updateCategory(@PathVariable int id, Model model){
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("categories", category.get());
            return "add-new-category";
        }else {
            return "404";
        }
    }


    @GetMapping("/products")
    public String getProduct(Model model){
        model.addAttribute("products", productService.getAllProduct());
        return "product-edit-page";
    }

    @GetMapping("/products/add")
    public String fillNewProduct(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "add-new-product";
    }

    @PostMapping("/products")
    public String addProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
                             @RequestParam("products") MultipartFile fileProductImage,
                             @RequestParam("imgName") String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if(!fileProductImage.isEmpty()){
            imageUUID = fileProductImage.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDirProduct, imageUUID);
            Files.write(fileNameAndPath, fileProductImage.getBytes());
        }else {
            imageUUID = imgName;
        }//save image
        product.setImageName(imageUUID);

        productService.updateProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/update/{id}")
    public String updateProduct(@PathVariable long id, Model model){
        Optional<Product> opProduct = productService.getProductById(id);
        if (opProduct.isPresent()){
            Product product = opProduct.get();
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setCategoryId(product.getCategory().getId());
            productDTO.setPrice(product.getPrice());
            productDTO.setWeight(product.getWeight());
            productDTO.setDescription(product.getDescription());
            productDTO.setImageName(product.getImageName());

            model.addAttribute("productDTO", productDTO);
            model.addAttribute("categories", categoryService.getAllCategory());
            return "add-new-product";
        }else {
            return "404";
        }
    }
}
