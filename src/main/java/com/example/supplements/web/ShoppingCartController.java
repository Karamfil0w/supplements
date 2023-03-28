package com.example.supplements.web;

import com.example.supplements.model.entities.Product;
import com.example.supplements.model.entities.ShoppingCart;
import com.example.supplements.services.ProductService;
import com.example.supplements.services.ShoppingCartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class ShoppingCartController {
    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ProductService productService, ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        model.addAttribute("cart", cart);
        model.addAttribute("totalSum",cart.getTotalSum());
        return "cart";
    }

    @GetMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable("productId") Long productId,
                            HttpSession session) {
        Optional<Product> product = productService.getProductById(productId);
        if (product.isPresent()) {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
                session.setAttribute("cart", cart);
            }
            cart.addProduct(product);
        }
        return "redirect:/cart";
    }

//    @PostMapping("/cart/update/{productId}")
//    public String updateCart(@PathVariable("productId") Long productId,
//                             @RequestParam("quantity") int quantity,
//                             HttpSession session) {
//        Optional<Product> product = productService.getProductById(productId);
//        if (product.isPresent()) {
//            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
//            if (cart != null) {
//                cart.updateItemQuantity(product, quantity);
//            }
//        }
//        return "redirect:/cart";
//    }

//    @PostMapping("/cart/remove/{productId}")
//    public String removeFromCart(@PathVariable("productId") Long productId,
//                                 HttpSession session) {
//        Optional<Product> product = productService.getProductById(productId);
//        if (product.isPresent()) {
//            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
//            if (cart != null) {
//                cart.removeItem(product);
//            }
//        }
//        return "redirect:/cart";
//    }
//}
}
