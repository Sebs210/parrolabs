package com.example.parrolabs.service;

import com.example.parrolabs.Interface.CustomerRepository;
import com.example.parrolabs.Interface.OrderRepository;
import com.example.parrolabs.Interface.ProductRepository;
import com.example.parrolabs.Utils.ProductAlreadyUsedException;
import com.example.parrolabs.Utils.ResourceNotFoundException;
import com.example.parrolabs.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
    }

    public Order createOrder(Order order) {
        // Validate customer and shipping address
        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", order.getCustomer().getId()));
        ShippingAddress shippingAddress = customer.getShippingAddressById(order.getShippingAddress().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Shipping Address", "id", order.getShippingAddress().getId()));

        // Validate order items
        BigDecimal totalValue = BigDecimal.ZERO;
        for (OrderItem orderItem : order.getOrderItems()) {
            Product product = productRepository.findById(orderItem.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product", "id", orderItem.getProduct().getId()));

            // Check that the product hasn't been used in an order before
            if (product.isUsed()) {
                throw new ProductAlreadyUsedException("Product with ID " + product.getId() + " has already been used in an order and cannot be deleted.");
            }

            // Calculate the order item value
            BigDecimal orderItemValue = product.getPrice().multiply(new BigDecimal(orderItem.getQuantity()));
            orderItem.setValue(orderItemValue);

            // Add the order item value to the total order value
            totalValue = totalValue.add(orderItemValue);
        }

        // Check that the order contains at least one order item
        if (order.getOrderItems().isEmpty()) {
            throw new EmptyOrderException("An order must contain at least one product.");
        }

        // Set the order total value
        order.setTotalValue(totalValue);

        // Save the order
        Order savedOrder = orderRepository.save(order);

        // Set the product as used
        for (OrderItem orderItem : savedOrder.getOrderItems()) {
            Product product = productRepository.findById(orderItem.getProduct().getId()).get();
            product.setUsed(true);
            productRepository.save(product);
        }

        return savedOrder;
    }

    public void cancelOrder(Long id) {
        Order order = getOrderById(id);

        if (order.getStatus() != OrderStatus.PLACED) {
            throw new ProductAlreadyUsedException("Order with ID " + id + " cannot be cancelled because it is not in 'PLACED' status.");
        }

        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

        // Set the products as unused
        for (OrderItem orderItem : order.getOrderItems()) {
            Product product = productRepository.findById(orderItem.getProduct().getId()).get();
            product.setUsed(false);
            productRepository.save(product);
        }
    }
}
