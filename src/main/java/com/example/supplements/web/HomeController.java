package com.example.supplements.web;

import com.example.supplements.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/home")
    public String home(Model model){

        model.addAttribute("proteins",productService.top3Proteins());
        return "home";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/";
    }
}
