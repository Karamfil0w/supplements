package com.example.supplements.services;

import com.example.supplements.model.entities.Category;
import com.example.supplements.model.enums.CategoryEnum;
import com.example.supplements.repositories.CategoryRepository;
import com.example.supplements.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
 private final CategoryRepository categoryRepository;
 private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }



    public Category findByType(CategoryEnum name) {
        return this.categoryRepository.findByType(name);
    }
}
