package com.example.supplements.services;

import com.example.supplements.model.dtos.MakeOrderDto;
import com.example.supplements.model.entities.Order;
import com.example.supplements.model.entities.Product;
import com.example.supplements.model.entities.ShoppingCart;
import com.example.supplements.repositories.OrderRepository;
import com.example.supplements.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private ProductRepository productRepository;


    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;

        this.productRepository = productRepository;
    }

    public void createOrder(MakeOrderDto makeOrderDto, ShoppingCart shoppingCart) {

        Order order = new Order();
        order.setFirstName(makeOrderDto.getFirstName());
        order.setLastName(makeOrderDto.getLastName());
        order.setEmail(makeOrderDto.getEmail());
        order.setAddress(makeOrderDto.getAddress());

        List<Product> products = shoppingCart.getProducts();

        order.setProducts(products);

        orderRepository.save(order);
    }

    public void confirmOrderFromAdmin(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            List<Product> products = order.getProducts();
            for (Product product : products) {
                if (product.isSoldOut()) {
                    this.productRepository.delete(product);
                }
            }
            this.orderRepository.delete(order);
        }
    }
    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public Optional<Order> findById(Long orderId) {
        return this.orderRepository.findById(orderId);
    }

    public void delete(Order order) {
        this.orderRepository.delete(order);
    }
}
