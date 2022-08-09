package com.project.spring.new_main_project.controller;

import com.project.spring.new_main_project.cart.MainCart;
import com.project.spring.new_main_project.model.Product;
import com.project.spring.new_main_project.repository.ActualProductRepository;
import com.project.spring.new_main_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/products")
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ActualProductRepository actualProductRepository;



    @GetMapping("category/{id}")
    public String getProductCategorie(@PathVariable int id, Model model){
        model.addAttribute("cartCount", MainCart.cart.size());
        model.addAttribute("products", productService.getAllProductByCategoryId(id));
        return "category-products";
    }



    @GetMapping("/detail-product/{id}")
    public String viewProduct(@PathVariable long id, Model model){
        model.addAttribute("cartCount", MainCart.cart.size());
        Product exclusiveProduct = productService.getProductById(id).get();
        model.addAttribute("product", productService.getProductById(id).get());
        model.addAttribute("actualProducts", actualProductRepository.findAll());
        return "detail-product";
    }


    @GetMapping("/actual-products/detail-product/{id}")
    public String viewActualProduct(@PathVariable long id, Model model){
        model.addAttribute("cartCount", MainCart.cart.size());
        model.addAttribute("actualProduct", actualProductRepository.getActualProductById(id).get());
        model.addAttribute("actualProducts", actualProductRepository.findAll());
        return "detail-actual-product";
    }
}
