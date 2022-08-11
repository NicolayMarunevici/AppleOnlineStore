package com.project.spring.new_main_project.controller;


import com.project.spring.new_main_project.cart.MainCart;
import com.project.spring.new_main_project.repository.ActualProductRepository;
import com.project.spring.new_main_project.service.ProductService;
import com.project.spring.new_main_project.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping({"/", "/home"})
@Controller
public class HomeController {

    private ActualProductRepository actualProductRepository;
    private ProductService productService;
    private ProductServiceImpl productServiceImpl;

    @Autowired
    public HomeController(ActualProductRepository actualProductRepository, ProductService productService, ProductServiceImpl productServiceImpl) {
        this.actualProductRepository = actualProductRepository;
        this.productService = productService;
        this.productServiceImpl = productServiceImpl;
    }

    @GetMapping
    public String getHome(Model model){
        model.addAttribute("cartCount", MainCart.cart.size());
        model.addAttribute("product", productService.getProductById(productServiceImpl.getIphoneIdFromCategory()).get());
        model.addAttribute("exclusive", productService.getProductById(productServiceImpl.getExclusiveProduct()).get());
        model.addAttribute("actualProducts", actualProductRepository.findAll());
        return "home";
    }
}
