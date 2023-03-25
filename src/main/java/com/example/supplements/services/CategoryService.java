package com.example.supplements.services;

import com.example.supplements.model.entities.Category;
import com.example.supplements.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
 private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findByType(String name) {

        return this.categoryRepository.findByType(name);
    }
}
