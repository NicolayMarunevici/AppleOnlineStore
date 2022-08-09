package com.project.spring.new_main_project.repository;

import com.project.spring.new_main_project.model.ActualProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActualProductRepository extends JpaRepository<ActualProduct, Integer> {

    Optional<ActualProduct> getActualProductById(long id);
}
