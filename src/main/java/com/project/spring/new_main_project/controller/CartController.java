package com.project.spring.new_main_project.controller;


import com.project.spring.new_main_project.cart.MainCart;
import com.project.spring.new_main_project.model.ActualProduct;
import com.project.spring.new_main_project.model.Product;
import com.project.spring.new_main_project.repository.ActualProductRepository;
import com.project.spring.new_main_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/cart")
@Controller
public class CartController {

    private ProductService productService;
    private ActualProductRepository actualProductRepository;


    @Autowired
    public CartController(ProductService productService, ActualProductRepository actualProductRepository){
        this.productService = productService;
        this.actualProductRepository = actualProductRepository;
    }

    @GetMapping
    public String getCart(Model model){
        model.addAttribute("cartCount", MainCart.cart.size());
        model.addAttribute("total", MainCart.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", MainCart.cart);
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable int id){
        MainCart.cart.add(productService.getProductById(id).get());
        return "redirect:/categories";
    }

    @GetMapping("/add/actual/{id}")
    public String addToCartActual(@PathVariable int id){
        ActualProduct actualProduct = actualProductRepository.getActualProductById(id).get();
        Product product = new Product();

        product.setId(actualProduct.getId());
        product.setName(actualProduct.getName());
        product.setWeight(actualProduct.getWeight());
        product.setPrice(actualProduct.getPrice());
        product.setCategory(actualProduct.getCategory());
        product.setDescription(actualProduct.getDescription());
        product.setImageName(actualProduct.getImageName());

        MainCart.cart.add(product);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{index}")
    public String cartItemRemove(@PathVariable int index){
        MainCart.cart.remove(index);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String getCheckout(Model model){
        model.addAttribute("cartCount", MainCart.cart.size());
        model.addAttribute("total", MainCart.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", MainCart.cart);
        return "checkout";
    }
}
