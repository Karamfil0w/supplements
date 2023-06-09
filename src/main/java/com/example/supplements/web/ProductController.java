package com.example.supplements.web;

import com.example.supplements.model.dtos.ProductDetailDto;
import com.example.supplements.model.entities.Product;
import com.example.supplements.services.ProductPopulationService;
import com.example.supplements.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ProductPopulationService productPopulationService;


    public ProductController(ProductService productService, ProductPopulationService productPopulationService) {
        this.productService = productService;
        this.productPopulationService = productPopulationService;
    }

    @GetMapping("/restock")
    public String restock() {
        productPopulationService.restock();
        return "redirect:/home";
    }

    @GetMapping("/products")
    public String products(Model model) {

        model.addAttribute("allProteins", this.productService.findAllProteins());

        model.addAttribute("allPerformance", this.productService.findAllPerformance());

        model.addAttribute("allWeightManagement", this.productService.findAllWeightManagement());

        model.addAttribute("allVitamins", this.productService.findAllVitamins());

        return "/products";
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/addProduct")
    public String addProduct(@AuthenticationPrincipal
                             UserDetails userDetails) {

        return "/addProduct";
    }

    @PostMapping("/addProduct")
    public String saveProduct(@Valid ProductDetailDto productDetailDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("productDetailDto", productDetailDto);
            redirectAttributes.addAttribute
                    ("org.springframework.validation.BindingResult.productDetailDto", bindingResult);
            return "redirect:/addProduct";
        }
        this.productService.addProduct(productDetailDto);
        return "redirect:/products";
    }

    @GetMapping("/productDetails/{id}")
    public String getProductDetails(@PathVariable("id") Long id, Model model) {
        // retrieve the product from the database
        Optional<Product> product = this.productService.getProductById(id);

        // add the product to the model
        model.addAttribute("product", product.get());

        return "productDetails";
    }

    @ModelAttribute("productDetailDto")
    public ProductDetailDto initProduct() {
        return new ProductDetailDto();
    }
}
