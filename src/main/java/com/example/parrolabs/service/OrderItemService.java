package com.example.parrolabs.service;

import com.example.parrolabs.Repository.OrderItemRepository;
import com.example.parrolabs.Utils.ResourceNotFoundException;
import com.example.parrolabs.entity.Order;
import com.example.parrolabs.entity.OrderItem;
import com.example.parrolabs.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final OrderService orderService;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository, ProductService productService, OrderService orderService) {
        this.orderItemRepository = orderItemRepository;
        this.productService = productService;
        this.orderService = orderService;
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        Product product = productService.getProductById((long) orderItem.getProduct().getId());
        Order order = orderService.getOrderById(orderItem.getOrder().getId());
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        order.getOrderItems().add(orderItem);
        orderService.updateOrder(order);
        return orderItemRepository.save(orderItem);
    }

    public OrderItem getOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Order item not found with id " + orderItemId));
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }


    public void deleteOrderItem(Long orderItemId) {
        OrderItem orderItem = getOrderItemById(orderItemId);
        Order order = orderItem.getOrder();
        order.getOrderItems().remove(orderItem);
        orderService.updateOrder(order);
        orderItemRepository.delete(orderItem);
    }

    public void deleteOrderItemsByOrderId(Long orderId) {
        Order order = orderService.getOrderById(orderId);
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem orderItem : orderItems) {
            orderItemRepository.delete(orderItem);
        }
    }



}

