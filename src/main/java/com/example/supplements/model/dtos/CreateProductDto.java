package com.example.supplements.model.dtos;
import com.example.supplements.model.enums.CategoryEnum;
import com.example.supplements.model.enums.FlavourEnum;

import java.math.BigDecimal;

public class CreateProductDto {
    private String name;

    private CategoryEnum category;

    private Double size;

    private FlavourEnum flavor;

    private String description;

    private String imageURL;

    private BigDecimal price;

    private boolean SoldOut;

    private int quantity;

    public CreateProductDto() {
    }

    public CreateProductDto(String name, CategoryEnum category,
                            Double size, FlavourEnum flavor,
                            String description, String imageURL, BigDecimal price, boolean soldOut, int quantity) {
        this.name = name;
        this.category = category;
        this.size = size;
        this.flavor = flavor;
        this.description = description;
        this.imageURL = imageURL;
        this.price = price;
        SoldOut = soldOut;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
        SoldOut = soldOut;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
