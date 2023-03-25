package com.example.supplements.model.entities;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table()
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "shoppingCart",
            fetch = FetchType.EAGER)
    private List<Product> products;

    @Column(nullable = false)
    private BigDecimal totalPrice;

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

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}
