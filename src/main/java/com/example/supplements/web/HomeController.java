package com.example.supplements.web;

import com.example.supplements.services.CategoryService;
import com.example.supplements.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/home")
    public String home(Model model){

        model.addAttribute("proteins",productService.findAllProteins());

        model.addAttribute("performance",productService.findAllPerformance());

        model.addAttribute("weightManagement",productService.findAllWeightManagement());

        model.addAttribute("vitamins",productService.findAllVitamins());

        return "home";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/";
    }
}
