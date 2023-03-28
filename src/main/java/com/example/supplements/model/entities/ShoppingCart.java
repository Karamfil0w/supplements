package com.example.supplements.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_cart_id")
    private Long id;

    @ManyToMany(mappedBy = "shoppingCarts")
    private List<Product> products = new ArrayList<>();

    @OneToOne
    private User user;


    public ShoppingCart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }



    public void addProduct(Optional<Product> product) {
        List<Product> currentProducts = getProducts();
        currentProducts.add(product.get());
    }
    public BigDecimal getTotalSum(){
        BigDecimal totalSum = new BigDecimal(0);
        List<Product> allProducts = getProducts();
        for (Product product : allProducts) {
            totalSum = totalSum.add(product.getPrice());
        }
        return totalSum;
    }
}
