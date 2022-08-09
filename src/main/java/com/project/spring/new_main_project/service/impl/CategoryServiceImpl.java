package com.project.spring.new_main_project.service.impl;


import com.project.spring.new_main_project.model.Category;
import com.project.spring.new_main_project.repository.CategoryRepository;
import com.project.spring.new_main_project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public void updateCategory(Category category){categoryRepository.save(category); }

    public void removeCategoryById(int id){
        categoryRepository.deleteById(id);
    }

    public Optional<Category> getCategoryById(int id){
        return categoryRepository.findById(id);
    }

}
