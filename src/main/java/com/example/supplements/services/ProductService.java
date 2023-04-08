package com.example.supplements.services;

import com.example.supplements.model.dtos.ProductDetailDto;
import com.example.supplements.model.entities.Category;
import com.example.supplements.model.entities.Product;
import com.example.supplements.model.entities.User;
import com.example.supplements.model.enums.CategoryEnum;
import com.example.supplements.model.enums.UserRoleEnum;
import com.example.supplements.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper,
                          CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;

    }

    private boolean isAdmin(User user) {
        return user.getUserRoles().
                stream().
                anyMatch(r -> r.getUserRole() == UserRoleEnum.ADMIN);
    }

    public void addProduct(ProductDetailDto productDetailDto) {

        Product product = this.modelMapper.map(productDetailDto, Product.class);
        CategoryEnum type = CategoryEnum.valueOf(productDetailDto.getCategory());
        Category category = this.categoryService.findByType(type);
        product.setCategory(category);

        this.productRepository.save(product);
    }

    public List<Product> findTop5Proteins() {
        List<Product> byCategoryTypeRandom5 = this.productRepository.findByCategoryType(CategoryEnum.PROTEIN);
        //random shuffle
        Collections.shuffle(byCategoryTypeRandom5);
        return getProducts(byCategoryTypeRandom5);
    }

    public List<Product> findTop5Performance() {
        List<Product> byCategoryTypeRandom5 = this.productRepository.findByCategoryType(CategoryEnum.PERFORMANCE);
        Collections.shuffle(byCategoryTypeRandom5);
        return getProducts(byCategoryTypeRandom5);
    }

    public List<Product> findTop5Weight_Management() {
        List<Product> byCategoryTypeRandom5 = this.productRepository.findByCategoryType(CategoryEnum.WEIGHT_MANAGEMENT);
        Collections.shuffle(byCategoryTypeRandom5);
        return getProducts(byCategoryTypeRandom5);
    }

    public List<Product> findTop5Vitamins() {
        List<Product> byCategoryTypeRandom5 = this.productRepository.findByCategoryType(CategoryEnum.VITAMINS);
        Collections.shuffle(byCategoryTypeRandom5);
        return getProducts(byCategoryTypeRandom5);
    }

    private static List<Product> getProducts(List<Product> byCategoryTypeRandom5) {
        List<Product> random5proteins = new ArrayList<>();
        for (Product product : byCategoryTypeRandom5) {
            if (random5proteins.size() == 5) {
                break;
            }
            if (!product.isSoldOut()) {
                random5proteins.add(product);
            }
        }
        return random5proteins;
    }

    public List<Product> findAllProteins() {
        List<Product> byCategoryType = this.productRepository.findByCategoryType(CategoryEnum.PROTEIN);
        return getProductList(byCategoryType);
    }

    public List<Product> findAllPerformance() {
        List<Product> byCategoryType = this.productRepository.findByCategoryType(CategoryEnum.PERFORMANCE);
        return getProductList(byCategoryType);
    }

    public List<Product> findAllWeightManagement() {

        List<Product> byCategoryType = this.productRepository.findByCategoryType(CategoryEnum.WEIGHT_MANAGEMENT);
        return getProductList(byCategoryType);
    }

    public List<Product> findAllVitamins() {
        List<Product> byCategoryType = this.productRepository.findByCategoryType(CategoryEnum.VITAMINS);
        return getProductList(byCategoryType);
    }

    private static List<Product> getProductList(List<Product> byCategoryType) {
        List<Product> allProteins = new ArrayList<>();
        for (Product product : byCategoryType) {
            if (!product.isSoldOut()) {
                allProteins.add(product);
            }
        }
        return allProteins;
    }

    public Optional<Product> getProductById(Long productId) {
        return this.productRepository.findById(productId);

    }

    public void save(Product product) {
        this.productRepository.save(product);
    }

    public void delete(Product product) {
        this.productRepository.delete(product);
    }

    public List<Product> getFiveRandomProducts() {

        List<Product> discountProducts = new ArrayList<>();

        List<Product> fiveRandomProducts = this.productRepository.getFiveRandomProducts();
        for (Product randomProduct : fiveRandomProducts) {
            randomProduct.setPrice(randomProduct.getPrice().divide(BigDecimal.valueOf(2)));
            discountProducts.add(randomProduct);
        }
        return discountProducts;
    }
}
