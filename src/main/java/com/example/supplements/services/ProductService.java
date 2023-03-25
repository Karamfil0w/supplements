package com.example.supplements.services;

import com.example.supplements.model.dtos.ProductDetailDto;
import com.example.supplements.model.entities.Product;
import com.example.supplements.model.entities.User;
import com.example.supplements.model.enums.CategoryEnum;
import com.example.supplements.model.enums.UserRoleEnum;
import com.example.supplements.repositories.ProductRepository;
import com.example.supplements.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
private final UserRepository userRepository;
    private final ProductRepository productRepository ;
    private final ModelMapper modelMapper;

    public ProductService(UserRepository userRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    private boolean isAdmin(User user) {
        return user.getUserRoles().
                stream().
                anyMatch(r -> r.getUserRole() == UserRoleEnum.ADMIN);
    }

    public void addProduct(ProductDetailDto productDetailDto) {

        Product product = this.modelMapper.map(productDetailDto, Product.class);
        productRepository.save(product);
    }

    public List<Product> top3Proteins(){
        return this.productRepository.findAllByCategory_Type(CategoryEnum.PROTEIN);
    }

//    public Page<ProductDetailDto> getAllProducts(Pageable pageable) {
//
//        return productRepository.
//                findAll(pageable).
//                map(modelMapper.map(Product.class,ProductDetailDto);
//
//    }

}
