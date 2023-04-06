package com.example.supplements.model.dtos;
import com.example.supplements.model.enums.CategoryEnum;
import com.example.supplements.model.enums.FlavourEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class CreateProductDto {
    @NotBlank
    @Size(min = 2)
    private String name;

    @NotBlank
    private CategoryEnum category;

    @NotBlank
    private Double size;
    @NotBlank
    private FlavourEnum flavor;
    @NotBlank
    private String description;
    @NotBlank
    private String ingredientsUrl;
    @NotBlank
    private String imageURL;

    @NotBlank
    private BigDecimal price;

    private boolean SoldOut;
    @NotBlank
    private int quantity;

    public CreateProductDto() {
    }

    public CreateProductDto(String name, CategoryEnum category,
                            Double size, FlavourEnum flavor,
                            String description, String imageURL, String ingredientsUrl, BigDecimal price, boolean soldOut, int quantity) {
        this.name = name;
        this.category = category;
        this.size = size;
        this.flavor = flavor;
        this.description = description;
        this.imageURL = imageURL;
        this.ingredientsUrl = ingredientsUrl;
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

    public String getIngredientsUrl() {
        return ingredientsUrl;
    }

    public void setIngredientsUrl(String ingredientsUrl) {
        this.ingredientsUrl = ingredientsUrl;
    }
}
