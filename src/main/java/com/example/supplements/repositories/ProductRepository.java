package com.example.supplements.repositories;

import com.example.supplements.model.entities.Product;
import com.example.supplements.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {


    List<Product> findByCategoryType(CategoryEnum categoryEnum);

}
