package com.example.supplements.web;

import com.example.supplements.model.dtos.MakeOrderDto;
import com.example.supplements.model.entities.Order;
import com.example.supplements.model.entities.Product;
import com.example.supplements.model.entities.ShoppingCart;
import com.example.supplements.services.OrderService;
import com.example.supplements.services.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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


    private final OrderService orderService;
    private final ProductService productService;

    public OrderController(OrderService orderService, ProductService productService) {

        this.orderService = orderService;
        this.productService = productService;

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
        orderService.confirmOrderFromAdmin(orderId);
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

