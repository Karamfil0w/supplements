package com.example.supplements.services;

import com.example.supplements.model.entities.Product;
import com.example.supplements.model.entities.ShoppingCart;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ProductService productService;

    public ShoppingCartService(ProductService productService) {
        this.productService = productService;
    }
    public void addProductToShoppingCart(Long productId, HttpSession session) {
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
    }

    public void cartSession (Long productId, HttpSession session) {
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
    }

}






