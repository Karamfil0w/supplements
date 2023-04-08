package com.example.supplements.services;

import com.example.supplements.model.dtos.ProductDetailDto;
import com.example.supplements.model.entities.Category;
import com.example.supplements.model.entities.Product;
import com.example.supplements.model.enums.CategoryEnum;
import com.example.supplements.model.enums.FlavourEnum;
import com.example.supplements.repositories.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    private Product sampleProduct;
    private Product soldOutSampleProduct;
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private CategoryService categoryService;


    private ProductService productService;


    @BeforeEach
    public void init() {
        sampleProduct= new Product();
        sampleProduct.setDescription("testDescription");
        sampleProduct.setImageURL("sampleUrl");
        sampleProduct.setName("sampleName");
        sampleProduct.setPrice(new BigDecimal(9999));
        sampleProduct.setFlavor(FlavourEnum.BANANA);
        sampleProduct.setSoldOut(false);

        soldOutSampleProduct= new Product();
        soldOutSampleProduct.setDescription("testDescription");
        soldOutSampleProduct.setImageURL("sampleUrl");
        soldOutSampleProduct.setName("sampleName");
        soldOutSampleProduct.setPrice(new BigDecimal(9999));
        soldOutSampleProduct.setFlavor(FlavourEnum.BANANA);
        soldOutSampleProduct.setSoldOut(true);
    }
    @Test
    void findTop5ProteinsShouldReturn5Proteins() {
        List<Product> productsToExpectFromDB = Arrays.asList(sampleProduct, sampleProduct, sampleProduct,
                sampleProduct, sampleProduct, sampleProduct, sampleProduct);

        when(productRepository.findByCategoryType(CategoryEnum.PROTEIN)).thenReturn(productsToExpectFromDB);

        List<Product> expectedProducts = Arrays.asList(sampleProduct, sampleProduct, sampleProduct,
                sampleProduct, sampleProduct);

        productService = new ProductService( productRepository, modelMapper, categoryService);

        List<Product> foundProducts = productService.findTop5Proteins();

        Assertions.assertThat(foundProducts).hasSameElementsAs(expectedProducts);
    }

    @Test
    void findTop5PerformanceShouldReturn5Performance() {
        List<Product> productsToExpectFromDB = Arrays.asList(sampleProduct, sampleProduct, sampleProduct,
                sampleProduct, sampleProduct, sampleProduct, sampleProduct);

        when(productRepository.findByCategoryType(CategoryEnum.PERFORMANCE)).thenReturn(productsToExpectFromDB);

        List<Product> expectedProducts = Arrays.asList(sampleProduct, sampleProduct, sampleProduct,
                sampleProduct, sampleProduct);

        productService = new ProductService( productRepository,  modelMapper,
                 categoryService);

        List<Product> foundProducts = productService.findTop5Performance();

        Assertions.assertThat(foundProducts).hasSameElementsAs(expectedProducts);
    }

    @Test
    void findTop5Weight_ManagementShouldReturn5Weight_Management() {
        List<Product> productsToExpectFromDB = Arrays.asList(sampleProduct, sampleProduct, sampleProduct,
                sampleProduct, sampleProduct, sampleProduct, sampleProduct);

        when(productRepository.findByCategoryType(CategoryEnum.WEIGHT_MANAGEMENT)).thenReturn(productsToExpectFromDB);

        List<Product> expectedProducts = Arrays.asList(sampleProduct, sampleProduct, sampleProduct,
                sampleProduct, sampleProduct);

        productService = new ProductService( productRepository, modelMapper, categoryService);

        List<Product> foundProducts = productService.findTop5Weight_Management();

        Assertions.assertThat(foundProducts).hasSameElementsAs(expectedProducts);
    }

    @Test
    void findTop5VitaminsShouldReturn5Vitamins() {
        List<Product> productsToExpectFromDB = Arrays.asList(sampleProduct, sampleProduct, sampleProduct,
                sampleProduct, sampleProduct, sampleProduct, sampleProduct);

        when(productRepository.findByCategoryType(CategoryEnum.VITAMINS)).thenReturn(productsToExpectFromDB);

        List<Product> expectedProducts = Arrays.asList(sampleProduct, sampleProduct, sampleProduct,
                sampleProduct, sampleProduct);

        productService = new ProductService( productRepository, modelMapper, categoryService);

        List<Product> foundProducts = productService.findTop5Vitamins();

        Assertions.assertThat(foundProducts).hasSameElementsAs(expectedProducts);
    }

    @Test
    void findAllProteinsShouldReturnAllProteinsThatAreNotSoldOut() {
        List<Product> productsToExpectFromDB = Arrays.asList(sampleProduct, sampleProduct, sampleProduct,
                soldOutSampleProduct, soldOutSampleProduct);

        when(productRepository.findByCategoryType(CategoryEnum.PROTEIN)).thenReturn(productsToExpectFromDB);

        List<Product> expectedProducts = Arrays.asList(sampleProduct, sampleProduct, sampleProduct);

        productService = new ProductService( productRepository, modelMapper, categoryService);

        List<Product> foundProducts = productService.findAllProteins();

        Assertions.assertThat(foundProducts).hasSameElementsAs(expectedProducts);
    }

    @Test
    void findAllPerformanceShouldReturnAllPerformanceThatAreNotSoldOut() {
        List<Product> productsToExpectFromDB = Arrays.asList(sampleProduct, sampleProduct, sampleProduct,
                soldOutSampleProduct, soldOutSampleProduct);

        when(productRepository.findByCategoryType(CategoryEnum.PERFORMANCE)).thenReturn(productsToExpectFromDB);

        List<Product> expectedProducts = Arrays.asList(sampleProduct, sampleProduct, sampleProduct);

        productService = new ProductService( productRepository, modelMapper, categoryService);

        List<Product> foundProducts = productService.findAllPerformance();

        Assertions.assertThat(foundProducts).hasSameElementsAs(expectedProducts);
    }

    @Test
    void findAllWeightManagementShouldReturnAllWeightManagementThatAreNotSoldOut() {
        List<Product> productsToExpectFromDB = Arrays.asList(sampleProduct, sampleProduct, sampleProduct,
                soldOutSampleProduct, soldOutSampleProduct);

        when(productRepository.findByCategoryType(CategoryEnum.WEIGHT_MANAGEMENT)).thenReturn(productsToExpectFromDB);

        List<Product> expectedProducts = Arrays.asList(sampleProduct, sampleProduct, sampleProduct);

        productService = new ProductService(productRepository, modelMapper, categoryService);

        List<Product> foundProducts = productService.findAllWeightManagement();

        Assertions.assertThat(foundProducts).hasSameElementsAs(expectedProducts);
    }

    @Test
    void addProduct_shouldCreateNewProduct() {

        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setName("Test Product");
        productDetailDto.setPrice(BigDecimal.valueOf(9.99));
        productDetailDto.setQuantity(10);
        productDetailDto.setCategory(CategoryEnum.VITAMINS.name());

        Category category = new Category();
        category.setId(1L);
        category.setType(CategoryEnum.VITAMINS);

        when(categoryService.findByType(CategoryEnum.VITAMINS)).thenReturn(category);

        Product expectedProduct = new Product();
        expectedProduct.setName("Test Product");
        expectedProduct.setPrice(BigDecimal.valueOf(9.99));
        expectedProduct.setQuantity(10);
        expectedProduct.setCategory(category);

        when(modelMapper.map(productDetailDto, Product.class)).thenReturn(expectedProduct);

        productService = new ProductService(productRepository, modelMapper, categoryService);

        productService.addProduct(productDetailDto);

        ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);
        Mockito.verify(productRepository).save(argumentCaptor.capture());

        Product actualProduct = argumentCaptor.getValue();
        Assertions.assertThat(actualProduct).isEqualTo(expectedProduct);
    }

}