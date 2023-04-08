package com.example.supplements.repositories;

import com.example.supplements.model.entities.Product;
import com.example.supplements.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {


    List<Product> findByCategoryType(CategoryEnum categoryEnum);

//    @Query(value = "SELECT p FROM Product p WHERE p.Category_Type ORDER BY RAND() LIMIT 5",
//            nativeQuery = true)
//    List<Product> get5RandomProductsByCategoryType(CategoryEnum categoryEnum);

    @Query(value = "SELECT * FROM supplements.products ORDER BY RAND() LIMIT 5",
            nativeQuery = true)
    List<Product> getFiveRandomProducts();

}
