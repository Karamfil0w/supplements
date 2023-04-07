package com.example.supplements.web;

import com.example.supplements.model.dtos.MakeOrderDto;
import com.example.supplements.model.entities.Order;
import com.example.supplements.model.entities.Product;
import com.example.supplements.model.entities.ShoppingCart;
import com.example.supplements.services.OrderService;
import com.example.supplements.services.ProductService;
import com.example.supplements.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    public OrderController(UserService userService, OrderService orderService, ProductService productService, ModelMapper modelMapper) {
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("makeOrderDto")
    public MakeOrderDto initUserModel() {
        return new MakeOrderDto();
    }

    @GetMapping("/orders")
    public String orders(Model model) {

        List<Order> orders = orderService.getAllOrders();


        model.addAttribute("orders", orders);

        return "/orders";
    }

    @PostMapping("/confirm-order")
    public String confirmOrder(@RequestParam Long orderId) {
        Optional<Order> optionalOrder = orderService.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            List<Product> products = order.getProducts();
            for (Product product : products) {
                if (product.isSoldOut()) {
                    this.productService.delete(product);
                }
            }

            this.orderService.delete(order);
        }
        return "redirect:/orders";
    }


    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }

    @PostMapping("/checkout")
    public String checkout(@Valid MakeOrderDto makeOrderDto,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           HttpSession session) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("makeOrderDto", makeOrderDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.makeOrderDto",
                    bindingResult);

            return "redirect:/checkout";
        }

        // Get the shopping cart from the session
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

        // Call the createOrder method in the OrderService with both the makeOrderDto and the shopping cart
        this.orderService.createOrder(makeOrderDto, cart);

        // Clear the shopping cart from the session
        session.setAttribute("cart", null);

        return "redirect:/home";
    }

}

