package com.project.spring.new_main_project.controller;

import com.project.spring.new_main_project.cart.MainCart;
import com.project.spring.new_main_project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/categories")
@Controller
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategories(Model model){
        model.addAttribute("cartCount", MainCart.cart.size());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }
}
