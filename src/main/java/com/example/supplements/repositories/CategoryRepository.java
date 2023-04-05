package com.example.supplements.repositories;

import com.example.supplements.model.entities.Category;
import com.example.supplements.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByType(CategoryEnum name);


}
