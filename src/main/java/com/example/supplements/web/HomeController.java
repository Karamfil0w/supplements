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

        model.addAttribute("TopProteins",productService.findTop5Proteins());

        model.addAttribute("TopPerformance",productService.findTop5Performance());

        model.addAttribute("TopWeightManagement",productService.findTop5Weight_Management());

        model.addAttribute("TopVitamins",productService.findTop5Vitamins());

        return "home";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/";
    }
}
