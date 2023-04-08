package com.example.supplements.services;

import com.example.supplements.model.entities.Product;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductScheduler {

    private final ProductService productService;

    public ProductScheduler(ProductService productService) {

        this.productService = productService;
    }
    List<Product> featuredProducts = new ArrayList<>();
    @Scheduled(fixedDelay = 3600000) // Run every hour
    public void updateFeaturedProducts() {
        List<Product> products = productService.getFiveRandomProducts();
        featuredProducts.clear();
        featuredProducts.addAll(products);
//        logger.info("Featured products updated: {}", featuredProducts);
    }

    public List<Product> getFeaturedProducts() {
        return featuredProducts;
    }
}
