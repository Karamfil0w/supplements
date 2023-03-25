package com.example.supplements.web;

import com.example.supplements.model.dtos.ProductDetailDto;
import com.example.supplements.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String products(){
        return "/products";
    }

    @GetMapping("/addProduct")
    public String addProduct(){
        return "/addProduct";
    }

    @PostMapping("/addProduct")
    public String saveProduct(@Valid ProductDetailDto productDetailDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("productDetailDto",productDetailDto);
            redirectAttributes.addAttribute
                    ("org.springframework.validation.BindingResult.productDetailDto",bindingResult);
            return "redirect:/addProduct";
        }
        this.productService.addProduct(productDetailDto);
        return "redirect:/products";
    }
    @ModelAttribute("productDetailDto")
    public ProductDetailDto initProduct(){
        return new ProductDetailDto();
    }
}
