package com.example.supplements.services;

import com.example.supplements.model.entities.Product;
import com.example.supplements.repositories.ProductRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductScheduler {

    private final ProductRepository productRepository;

    public ProductScheduler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void getFiveRandomProducts() {
        List<Product> products = productRepository.getFiveRandomProducts();

    }
}
