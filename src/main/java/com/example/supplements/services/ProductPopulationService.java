package com.example.supplements.services;

import com.example.supplements.model.dtos.CreateProductDto;
import com.example.supplements.model.entities.Category;
import com.example.supplements.model.entities.Product;
import com.example.supplements.model.enums.CategoryEnum;
import com.example.supplements.model.enums.FlavourEnum;
import com.example.supplements.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductPopulationService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public ProductPopulationService(ProductRepository productRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    public void restock() {
        //Restock Proteins
        create(new CreateProductDto("Whey protein", CategoryEnum.PROTEIN,2.50, FlavourEnum.CHOCOLATE,"Best Protein 1",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/41MV1eWGXoL.jpg",
                "hhttps://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(100.0),false, 2));
        create(new CreateProductDto("Casein protein",CategoryEnum.PROTEIN,2.50, FlavourEnum.BANANA,"Best Protein 2",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/41MV1eWGXoL.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(100.0),false, 2));
        create(new CreateProductDto("Matrix protein",CategoryEnum.PROTEIN,2.50, FlavourEnum.PEANUT_BUTTER,"Best Protein 3",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/41MV1eWGXoL.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(100.0),false, 2));
        create(new CreateProductDto("Whey protein", CategoryEnum.PROTEIN, 2.50, FlavourEnum.WHITE_CHOCOLATE, "Best Protein 4",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/41MV1eWGXoL.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(100.0), false, 2));
        create(new CreateProductDto("Whey protein",CategoryEnum.PROTEIN,2.50, FlavourEnum.STRAWBERRY,"Best Protein 5",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/41MV1eWGXoL.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(100.0),false, 2));
        create(new CreateProductDto("Whey protein",CategoryEnum.PROTEIN,2.50, FlavourEnum.COOKIES_AND_CREAM,"Best Protein 6",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/41MV1eWGXoL.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(100.0),false, 2));

        //Restock PERFORMANCE
        create(new CreateProductDto("Pre-workout",CategoryEnum.PERFORMANCE,0.500,
                FlavourEnum.STRAWBERRY,"Best pre-workout supplement",
                "https://www.nepal.ubuy.com/productimg/?image=aHR0cHM6Ly9tLm1lZGlhLWFtYXpvbi5jb20vaW1hZ2VzL0kvNjFpOEdHOGlIMUwuX0FDX1NMMTM5MV8uanBn.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50),false, 2));
        create(new CreateProductDto("Creatine",CategoryEnum.PERFORMANCE,0.500,
                FlavourEnum.BANANA,"Best pre-workout supplement",
                "https://www.nepal.ubuy.com/productimg/?image=aHR0cHM6Ly9tLm1lZGlhLWFtYXpvbi5jb20vaW1hZ2VzL0kvNjFpOEdHOGlIMUwuX0FDX1NMMTM5MV8uanBn.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50),false, 2));

        //Restock WEIGHT_MANAGEMENT
        create(new CreateProductDto("L-Carnitine",CategoryEnum.WEIGHT_MANAGEMENT,0.500,
                FlavourEnum.UNFLAVORED,"Best L-Carnitine ever",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/71u16lVHFUL.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50),false, 2));

        //Restock VITAMINS
        create(new CreateProductDto("Multivitamins",CategoryEnum.VITAMINS, 0.150,
                FlavourEnum.UNFLAVORED,"Best Vitamins ever",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/61tX2tcBF4L.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), false, 2));
    }

    private boolean create(CreateProductDto createProductDto) {

        Product product = this.modelMapper.map(createProductDto, Product.class);

        if (product == null) {
            return false;
        } else {
            Category byType = this.categoryService.findByType(createProductDto.getCategory());

            product.setCategory(byType);

            this.productRepository.save(product);
            return true;
        }
    }
}
