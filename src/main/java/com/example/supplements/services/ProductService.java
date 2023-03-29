package com.example.supplements.services;

import com.example.supplements.model.dtos.CreateProductDto;
import com.example.supplements.model.dtos.ProductDetailDto;
import com.example.supplements.model.entities.Category;
import com.example.supplements.model.entities.Product;
import com.example.supplements.model.entities.User;
import com.example.supplements.model.enums.CategoryEnum;
import com.example.supplements.model.enums.FlavourEnum;
import com.example.supplements.model.enums.UserRoleEnum;
import com.example.supplements.repositories.ProductRepository;
import com.example.supplements.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public List<Product> findAllProteins() {
        return this.productRepository.findByCategoryType(CategoryEnum.PROTEIN);
    }public List<Product> findAllPerformance() {
        return this.productRepository.findByCategoryType(CategoryEnum.PERFORMANCE);
    }public List<Product> findAllWeightManagement() {
        return this.productRepository.findByCategoryType(CategoryEnum.WEIGHT_MANAGEMENT);
    }public List<Product> findAllVitamins() {
        return this.productRepository.findByCategoryType(CategoryEnum.VITAMINS);
    }

    public Optional<Product> getProductById(Long productId) {
        return this.productRepository.findById(productId);
    }

    public void restock() {
        //Restock Proteins
        create(new CreateProductDto("Whey protein",CategoryEnum.PROTEIN,2.50, FlavourEnum.CHOCOLATE,"Best Protein 1",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/41MV1eWGXoL.jpg",
                BigDecimal.valueOf(100.0),false));
        create(new CreateProductDto("Casein protein",CategoryEnum.PROTEIN,2.50, FlavourEnum.BANANA,"Best Protein 2",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/41MV1eWGXoL.jpg",
                BigDecimal.valueOf(100.0),false));
        create(new CreateProductDto("Matrix protein",CategoryEnum.PROTEIN,2.50, FlavourEnum.PEANUT_BUTTER,"Best Protein 3",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/41MV1eWGXoL.jpg",
                BigDecimal.valueOf(100.0),false));
        create(new CreateProductDto("Whey protein", CategoryEnum.PROTEIN, 2.50, FlavourEnum.WHITE_CHOCOLATE, "Best Protein 4",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/41MV1eWGXoL.jpg",
                BigDecimal.valueOf(100.0), false));
        create(new CreateProductDto("Whey protein",CategoryEnum.PROTEIN,2.50, FlavourEnum.STRAWBERRY,"Best Protein 5",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/41MV1eWGXoL.jpg",
                BigDecimal.valueOf(100.0),false));
        create(new CreateProductDto("Whey protein",CategoryEnum.PROTEIN,2.50, FlavourEnum.COOKIES_AND_CREAM,"Best Protein 6",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/41MV1eWGXoL.jpg",
                BigDecimal.valueOf(100.0),false));

        //Restock PERFORMANCE
        create(new CreateProductDto("Pre-workout",CategoryEnum.PERFORMANCE,0.500,
                FlavourEnum.STRAWBERRY,"Best pre-workout supplement",
                "https://www.nepal.ubuy.com/productimg/?image=aHR0cHM6Ly9tLm1lZGlhLWFtYXpvbi5jb20vaW1hZ2VzL0kvNjFpOEdHOGlIMUwuX0FDX1NMMTM5MV8uanBn.jpg",
                BigDecimal.valueOf(50),false));
        create(new CreateProductDto("Creatine",CategoryEnum.PERFORMANCE,0.500,
                FlavourEnum.BANANA,"Best pre-workout supplement",
                "https://www.nepal.ubuy.com/productimg/?image=aHR0cHM6Ly9tLm1lZGlhLWFtYXpvbi5jb20vaW1hZ2VzL0kvNjFpOEdHOGlIMUwuX0FDX1NMMTM5MV8uanBn.jpg",
                BigDecimal.valueOf(50),false));

        //Restock WEIGHT_MANAGEMENT
        create(new CreateProductDto("L-Carnitine",CategoryEnum.WEIGHT_MANAGEMENT,0.500,
                FlavourEnum.UNFLAVORED,"Best L-Carnitine ever",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/71u16lVHFUL.jpg",
                BigDecimal.valueOf(50),false));

        //Restock VITAMINS
        create(new CreateProductDto("Multivitamins",CategoryEnum.VITAMINS, 0.150,
        FlavourEnum.UNFLAVORED,"Best Vitamins ever",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/61tX2tcBF4L.jpg",
                BigDecimal.valueOf(50), false));
    }

    private boolean create(CreateProductDto createProductDto) {

        Product product = this.modelMapper.map(createProductDto, Product.class);

        if (product == null) {
            return false;
        } else {
            Category category = new Category(createProductDto.getCategory());


            product.setCategory(category);

            this.productRepository.save(product);
            return true;
        }
    }


//    public Page<ProductDetailDto> getAllProducts(Pageable pageable) {
//
//        return productRepository.
//                findAll(pageable).
//                map(modelMapper.map(Product.class,ProductDetailDto);
//
//    }

}
