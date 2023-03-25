package com.example.supplements.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShoppingCartController {
    @GetMapping("/cart")
    public String shoppingCart(){
        return "/cart";
    }
}
