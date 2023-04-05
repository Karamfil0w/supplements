package com.example.supplements.model.entities;

import com.example.supplements.model.enums.FlavourEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Category category;  //name, category, size, flavour, description, imageURL, price, soldOut
    @Column(nullable = false)
    private Double size;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private FlavourEnum flavor;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private String imageURL;
    @Column(nullable = false)
    private BigDecimal price;

    private int quantity;
    @Column(nullable = false)
    private boolean SoldOut;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public Product() {
    }

    public Product(Order order) {
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public FlavourEnum getFlavor() {
        return flavor;
    }

    public void setFlavor(FlavourEnum flavor) {
        this.flavor = flavor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isSoldOut() {
        return SoldOut;
    }

    public void setSoldOut(boolean soldOut) {
        if (this.quantity == 0){
            soldOut = true;
        }
        SoldOut = soldOut;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        if (quantity>0){
            setSoldOut(false);
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
