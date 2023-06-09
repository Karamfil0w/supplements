package com.example.supplements.web;

import com.example.supplements.model.entities.Product;
import com.example.supplements.services.ProductScheduler;
import com.example.supplements.services.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;
    private final ProductScheduler productScheduler;

    public HomeController(ProductService productService, ProductScheduler productScheduler) {
        this.productService = productService;
        this.productScheduler = productScheduler;
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping("/")
    public String homeNotLoggedIn(Model model) {
        List<Product> featuredProducts = productScheduler.getFeaturedProducts();
        model.addAttribute("featuredProducts", featuredProducts);
        return "index";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/home")
    public String home(Model model){

        model.addAttribute("TopProteins",productService.findTop5Proteins());

        model.addAttribute("TopPerformance",productService.findTop5Performance());

        model.addAttribute("TopWeightManagement",productService.findTop5Weight_Management());

        model.addAttribute("TopVitamins",productService.findTop5Vitamins());

        return "home";
    }

    @GetMapping("allProteins")
    public String allProteins(Model model){
        model.addAttribute("allProteins",productService.findAllProteins());
        return "allProteins";
    }
    @GetMapping("allPerformance")
    public String allPerformance(Model model){
        model.addAttribute("allPerformance",productService.findAllPerformance());
        return "allPerformance";
    }
    @GetMapping("allWeightManagement")
    public String allWeightManagement(Model model){

        model.addAttribute("allWeightManagement",productService.findAllWeightManagement());
        return "allWeightManagement";
    }
    @GetMapping("allVitamins")
    public String allVitamins(Model model){
        model.addAttribute("allVitamins",productService.findAllVitamins());
        return "allVitamins";
    }


    @GetMapping("/logout")
    public String logout(){
        return "redirect:/";
    }
}
