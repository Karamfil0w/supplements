package com.example.supplements.services;

import com.example.supplements.model.dtos.ProductDetailDto;
import com.example.supplements.model.entities.Category;
import com.example.supplements.model.entities.Product;
import com.example.supplements.model.entities.User;
import com.example.supplements.model.enums.CategoryEnum;
import com.example.supplements.model.enums.UserRoleEnum;
import com.example.supplements.repositories.ProductRepository;
import com.example.supplements.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final ShoppingCartService cartService;

    public ProductService(UserRepository userRepository, ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService, ShoppingCartService cartService) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.cartService = cartService;
    }

    private boolean isAdmin(User user) {
        return user.getUserRoles().
                stream().
                anyMatch(r -> r.getUserRole() == UserRoleEnum.ADMIN);
    }

    public void addProduct(ProductDetailDto productDetailDto) {

        Product product = this.modelMapper.map(productDetailDto, Product.class);
        CategoryEnum type = CategoryEnum.valueOf(productDetailDto.getCategory());
        Category category = new Category(type);
        product.setCategory(category);
        productRepository.save(product);
    }

    public List<Product> top3Proteins() {
        return this.productRepository.findAllByCategory_Type(CategoryEnum.PROTEIN);
    }

    public Optional<Product> getProductById(Long productId) {
        return this.productRepository.findById(productId);
    }


//    public Page<ProductDetailDto> getAllProducts(Pageable pageable) {
//
//        return productRepository.
//                findAll(pageable).
//                map(modelMapper.map(Product.class,ProductDetailDto);
//
//    }

}
