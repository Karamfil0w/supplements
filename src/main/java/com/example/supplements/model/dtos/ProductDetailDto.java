package com.example.supplements.model.dtos;

import com.example.supplements.model.enums.FlavourEnum;

import java.math.BigDecimal;

public class ProductDetailDto {
    private Long id;
    private String name;
    private Double size;
    private String category;
    private FlavourEnum flavor;
    private String description;
    private String imageURL;

    private String ingredientsUrl;
    private BigDecimal price;
    private boolean SoldOut;

    public ProductDetailDto() {
    }

    public ProductDetailDto(Long id, String name, Double size,
                            FlavourEnum flavor, String description,
                            String imageURL, String ingredientsUrl, BigDecimal price, boolean soldOut) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.flavor = flavor;
        this.description = description;
        this.imageURL = imageURL;
        this.ingredientsUrl = ingredientsUrl;
        this.price = price;
        SoldOut = soldOut;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIngredientsUrl() {
        return ingredientsUrl;
    }

    public void setIngredientsUrl(String ingredientsUrl) {
        this.ingredientsUrl = ingredientsUrl;
    }
}
