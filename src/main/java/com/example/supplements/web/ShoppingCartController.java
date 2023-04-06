package com.example.supplements.web;

import com.example.supplements.model.entities.Product;
import com.example.supplements.model.entities.ShoppingCart;
import com.example.supplements.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ShoppingCartController {
    private final ProductService productService;

    public ShoppingCartController(ProductService productService) {
        this.productService = productService;

    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        model.addAttribute("cart", cart);
        model.addAttribute("totalSum", cart.getTotalSum());
        return "cart";
    }

    @GetMapping("/addToCart/{productId}")
    public String addToCart(@PathVariable("productId") Long productId,
                            HttpSession session) {
        var product = productService.getProductById(productId);
        if (product.isPresent()) {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
                session.setAttribute("cart", cart);
            }
            product.get().setQuantity(product.get().getQuantity()-1);
            productService.save(product.get());
            cart.addProduct(product);
        }

        return "redirect:/home";
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam("id") Long productId, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null) {
            List<Product> products = cart.getProducts();
            for (Product product : products) {
                if (product.getId().equals(productId)) {

                    product.setQuantity(product.getQuantity()+1);

                    productService.save(product);
                    products.remove(product);
                    break;
                }
            }
//            products.removeIf(p -> p.getId().equals(productId));
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }

}
