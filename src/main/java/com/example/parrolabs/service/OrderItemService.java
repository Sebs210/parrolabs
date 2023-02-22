package com.example.parrolabs.service;

import com.example.parrolabs.Interface.OrderItemRepository;
import com.example.parrolabs.Utils.ResourceNotFoundException;
import com.example.parrolabs.entity.OrderItem;
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
        Product product = productService.getProductById(orderItem.getProduct().getId());
        Order order = orderService.getOrderById(orderItem.getOrder().getId());
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        BigDecimal price = product.getPrice().multiply(new BigDecimal(orderItem.getQuantity()));
        orderItem.setPrice(price);
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

    public OrderItem updateOrderItem(OrderItem orderItem) {
        OrderItem existingOrderItem = getOrderItemById(orderItem.getId());
        existingOrderItem.setQuantity(orderItem.getQuantity());
        BigDecimal price = existingOrderItem.getProduct().getPrice().multiply(new BigDecimal(existingOrderItem.getQuantity()));
        existingOrderItem.setPrice(price);
        return orderItemRepository.save(existingOrderItem);
    }

    public void deleteOrderItem(Long orderItemId) {
        OrderItem orderItem = getOrderItemById(orderItemId);
        Order order = orderItem.getOrder();
        order.getOrderItems().remove(orderItem);
        orderService.updateOrder(order);
        orderItemRepository.delete(orderItem);
    }
}

