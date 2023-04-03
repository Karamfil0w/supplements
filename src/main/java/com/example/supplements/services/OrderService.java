package com.example.supplements.services;

import com.example.supplements.model.dtos.MakeOrderDto;
import com.example.supplements.model.entities.Order;
import com.example.supplements.model.entities.Product;
import com.example.supplements.model.entities.ShoppingCart;
import com.example.supplements.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(MakeOrderDto makeOrderDto, ShoppingCart shoppingCart) {

        Order order = new Order();
        order.setFirstName(makeOrderDto.getFirstName());
        order.setLastName(makeOrderDto.getLastName());
        order.setEmail(makeOrderDto.getEmail());
        order.setAddress(makeOrderDto.getAddress());

        List<Product> products = shoppingCart.getProducts();

        order.setProducts(products);

        // Save the order to the database
        orderRepository.save(order);
    }

}
