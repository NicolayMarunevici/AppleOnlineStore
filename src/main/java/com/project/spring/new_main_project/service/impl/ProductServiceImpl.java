package com.project.spring.new_main_project.service.impl;


import com.project.spring.new_main_project.model.Category;
import com.project.spring.new_main_project.model.Product;
import com.project.spring.new_main_project.repository.ProductRepository;
import com.project.spring.new_main_project.service.CategoryService;
import com.project.spring.new_main_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    public void removeProductById(long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProductByCategoryId(int id) {
        return productRepository.findAllByCategory_Id(id);
    }


    public int getIphoneIdFromCategory(){
        List<Category> categories = categoryService.getAllCategory();
        int iPhoneId = 0;

        for (Category category : categories) {
            if(category.getName().equals("IPhone")){
                iPhoneId = category.getId();
            }
        }
        return iPhoneId;
    }


    public Long getExclusiveProduct(){
        List<Product> products = productService.getAllProduct();
        Long exclusive = 0L;

        for (Product exclusiveSW : products) {
            if(exclusiveSW.getImageName().equals("exclusive.png")){
                exclusive = exclusiveSW.getId();
            }
        }
        return exclusive;
    }

}
