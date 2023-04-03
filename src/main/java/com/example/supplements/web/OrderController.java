package com.example.supplements.web;

import com.example.supplements.model.dtos.MakeOrderDto;
import com.example.supplements.model.entities.ShoppingCart;
import com.example.supplements.services.OrderService;
import com.example.supplements.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    public OrderController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @ModelAttribute("makeOrderDto")
    public MakeOrderDto initUserModel() {
        return new MakeOrderDto();
    }

    @GetMapping("/checkout")
    public String checkout(){
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


//    private BigDecimal calculateOrderTotal(MakeOrderDto makeOrderDto) {
//        BigDecimal total = BigDecimal.ZERO;
//        for (ProductDto product : makeOrderDto.getProducts()) {
//            total = total.add(product.getPrice());
//        }
//        return total;
//    }
}

