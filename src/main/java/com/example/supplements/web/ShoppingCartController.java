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
import java.util.Optional;

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

    @GetMapping("/cart/add/{productId}")
    public String addToCart(@PathVariable("productId") Long productId,
                            HttpSession session) {
        Optional<Product> product = productService.getProductById(productId);

        if (product.isPresent()) {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCart();
                session.setAttribute("cart", cart);
            }
            int quantity = product.get().getQuantity();

            if (quantity > 0) {
                product.get().setQuantity(product.get().getQuantity() - 1);
            }
            productService.save(product.get());
            cart.addProduct(product);
        }
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam("id") Long productId, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        Optional<Product> productById = productService.getProductById(productId);

        if (cart != null) {
            List<Product> products = cart.getProducts();
            for (Product product : products) {
                if (product.getId().equals(productId)) {

                    productById.get().setQuantity(productById.get().getQuantity() + 1);
                    productById.get().setSoldOut(false);
                    productService.save(productById.get());
                    products.remove(product);
                    break;
                }
            }
            session.setAttribute("cart", cart);
        }
        return "redirect:/cart";
    }

}
