package com.project.spring.new_main_project.controller;

import com.project.spring.new_main_project.cart.MainCart;
import com.project.spring.new_main_project.dto.UserDTO;
import com.project.spring.new_main_project.model.ContactForm;
import com.project.spring.new_main_project.model.Role;
import com.project.spring.new_main_project.model.User;
import com.project.spring.new_main_project.repository.ContactFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {

    private ContactFormRepository contactFormRepository;

    @Autowired
    public MenuController(ContactFormRepository contactFormRepository) {
        this.contactFormRepository = contactFormRepository;
    }

    @GetMapping("/contacts")
    public String getContact(Model model){
        model.addAttribute("cartCount", MainCart.cart.size());
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }


    @GetMapping("/about")
    public String getAbout(Model model){
        model.addAttribute("cartCount", MainCart.cart.size());
        return "about";
    }



    @PostMapping("/contact-form")
    public String postContactForm(@ModelAttribute("contactForm") ContactForm contactForm) {
        contactFormRepository.save(contactForm);
        return "redirect:/contacts";
    }
}


