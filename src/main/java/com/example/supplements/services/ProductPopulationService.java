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
        create(new CreateProductDto("Optimum Nutrition", CategoryEnum.PROTEIN,2.50, FlavourEnum.CHOCOLATE,"Best Protein 1",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/41MV1eWGXoL.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(100.0), 2));
        create(new CreateProductDto("Casein protein",CategoryEnum.PROTEIN,2.50, FlavourEnum.BANANA,"Best Protein 2",
                "https://cdn.shopify.com/s/files/1/0559/7161/0802/products/BBCOM_NEW_Signature_Whey_Van_5LB_V2_1123_grey_465x.jpg?v=1678388993",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(100.0), 2));
        create(new CreateProductDto("Matrix protein",CategoryEnum.PROTEIN,2.50, FlavourEnum.PEANUT_BUTTER,"Best Protein 3",
                "https://cdn.shopify.com/s/files/1/0559/7161/0802/products/3lb-FBrownie-DYM3610014-6721_grey_465x.jpg?v=1680877535",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(100.0), 2));
        create(new CreateProductDto("Whey protein", CategoryEnum.PROTEIN, 2.50, FlavourEnum.WHITE_CHOCOLATE, "Best Protein 4",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/41MV1eWGXoL.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(100.0),  2));
        create(new CreateProductDto("JYM Supplement",CategoryEnum.PROTEIN,2.50, FlavourEnum.STRAWBERRY,"Best Protein 5",
                "https://cdn.shopify.com/s/files/1/0559/7161/0802/products/JYM6340014_grey_465x.jpg?v=1658437032",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(100.0),2));
        create(new CreateProductDto("Signature Isolate",CategoryEnum.PROTEIN,2.50, FlavourEnum.COOKIES_AND_CREAM,"Best Protein 6",
                "https://cdn.shopify.com/s/files/1/0559/7161/0802/products/BBCOM_NEW_Signature_ISOWhey_Van_5LB_1123_grey_465x.jpg?v=1652946518",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(100.0), 2));

        //Restock PERFORMANCE
        create(new CreateProductDto("Pre-workout",CategoryEnum.PERFORMANCE,0.500,
                FlavourEnum.STRAWBERRY,"Best pre-workout supplement",
                "https://storage.googleapis.com/bodybuilding-cms/media/2021/11/699b3ace-pharma-freak-achilles-freak-peach-rings-image_large-e1675466445929.webp",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
        create(new CreateProductDto("Pre-workout",CategoryEnum.PERFORMANCE,0.500,
                FlavourEnum.STRAWBERRY,"Best pre-workout supplement",
                "https://storage.googleapis.com/bodybuilding-cms/media/2021/09/1e626a11-swolverine-pre-workout-e1679694132628.webp",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
        create(new CreateProductDto("Creatine",CategoryEnum.PERFORMANCE,0.500,
                FlavourEnum.BANANA,"Best creatine supplement",
                "https://cdn.shopify.com/s/files/1/0559/7161/0802/products/Creatine-Chews-Grape-1-grey_465x.jpg?v=1669232626",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
        create(new CreateProductDto("Creatine",CategoryEnum.PERFORMANCE,0.500,
                FlavourEnum.BANANA,"Best creatine supplement",
                "https://cdn.shopify.com/s/files/1/0559/7161/0802/products/COBRA6480466-JNXSPORT-Creatine-grey_465x.jpg?v=1657822579",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
        create(new CreateProductDto("Creatine",CategoryEnum.PERFORMANCE,0.500,
                FlavourEnum.BANANA,"Best creatine supplement",
                "https://cdn.shopify.com/s/files/1/0559/7161/0802/products/Animal-Creatine-XLFruit-Punch-30serv-image-grey_465x.jpg?v=1669232622",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
        create(new CreateProductDto("Xtend",CategoryEnum.PERFORMANCE,0.500,
                FlavourEnum.BANANA,"Best BCAA supplement",
                "https://cdn.shopify.com/s/files/1/0559/7161/0802/products/Xtend-Original-WE90-Front-Hires-grey_465x.jpg?v=1679493724",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));

        //Restock WEIGHT_MANAGEMENT
        create(new CreateProductDto("L-Carnitine",CategoryEnum.WEIGHT_MANAGEMENT,0.500,
                FlavourEnum.UNFLAVORED,"Best L-Carnitine ever",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/71u16lVHFUL.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
        create(new CreateProductDto("Fat Burner",CategoryEnum.WEIGHT_MANAGEMENT,0.500,
                FlavourEnum.UNFLAVORED,"Best Fat Burner ever",
                "https://cdn.shopify.com/s/files/1/0559/7161/0802/products/Animal-CUTSNS-Blue-Ice-Pop-image-grey_465x.jpg?v=1676056481",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
        create(new CreateProductDto("Fat Burner",CategoryEnum.WEIGHT_MANAGEMENT,0.500,
                FlavourEnum.UNFLAVORED,"Best Fat Burner ever",
                "https://cdn.shopify.com/s/files/1/0559/7161/0802/products/Animal-CUTSNS-Blue-Ice-Pop-image-grey_465x.jpg?v=1676056481",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
        create(new CreateProductDto("Fat Burner",CategoryEnum.WEIGHT_MANAGEMENT,0.500,
                FlavourEnum.UNFLAVORED,"Best Fat Burner ever",
                "https://cdn.shopify.com/s/files/1/0559/7161/0802/products/Animal-CUTSNS-Blue-Ice-Pop-image-grey_465x.jpg?v=1676056481",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
        create(new CreateProductDto("Fat Burner",CategoryEnum.WEIGHT_MANAGEMENT,0.500,
                FlavourEnum.UNFLAVORED,"Best Fat Burner ever",
                "https://cdn.shopify.com/s/files/1/0559/7161/0802/products/Animal-CUTSNS-Blue-Ice-Pop-image-grey_465x.jpg?v=1676056481",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));

        //Restock VITAMINS
        create(new CreateProductDto("Multivitamins",CategoryEnum.VITAMINS, 0.150,
                FlavourEnum.UNFLAVORED,"Best Vitamins ever",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/61tX2tcBF4L.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
        create(new CreateProductDto("Multivitamins",CategoryEnum.VITAMINS, 0.150,
                FlavourEnum.UNFLAVORED,"Best Vitamins ever",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/61tX2tcBF4L.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
        create(new CreateProductDto("Multivitamins",CategoryEnum.VITAMINS, 0.150,
                FlavourEnum.UNFLAVORED,"Best Vitamins ever",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/61tX2tcBF4L.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
        create(new CreateProductDto("Multivitamins",CategoryEnum.VITAMINS, 0.150,
                FlavourEnum.UNFLAVORED,"Best Vitamins ever",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/61tX2tcBF4L.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
        create(new CreateProductDto("Multivitamins",CategoryEnum.VITAMINS, 0.150,
                FlavourEnum.UNFLAVORED,"Best Vitamins ever",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/61tX2tcBF4L.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
        create(new CreateProductDto("Multivitamins",CategoryEnum.VITAMINS, 0.150,
                FlavourEnum.UNFLAVORED,"Best Vitamins ever",
                "https://m.media-amazon.com/images/W/IMAGERENDERING_521856-T1/images/I/61tX2tcBF4L.jpg",
                "https://www.shutterstock.com/image-vector/nutrition-facts-design-label-vector-260nw-1660605661.jpg",
                BigDecimal.valueOf(50), 2));
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
