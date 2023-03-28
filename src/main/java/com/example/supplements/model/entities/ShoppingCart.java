package com.example.supplements.model.entities;

import jakarta.persistence.*;

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
    private List<Product> products;

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



    public void addProduct(Optional<Product> product, int quantity) {
        List<Product> currentProducts = getProducts();
        currentProducts.add(product.get());
    }
}
