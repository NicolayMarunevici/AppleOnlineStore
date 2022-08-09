package com.project.spring.new_main_project.service;


import com.project.spring.new_main_project.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
     List<Product> getAllProduct();
     void updateProduct(Product product);
     void removeProductById(long id);
     Optional<Product> getProductById(long id);
     List<Product> getAllProductByCategoryId(int id);
}
