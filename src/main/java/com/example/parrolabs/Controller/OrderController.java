package com.example.parrolabs.Controller;

import com.example.parrolabs.Request.OrderRequest;
import com.example.parrolabs.service.OrderService;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<SpringDataJaxb.OrderDto> createOrder(@RequestBody OrderRequest request) {
        SpringDataJaxb.OrderDto order = orderService.createOrder(request);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<SpringDataJaxb.OrderDto> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequest request) {
        SpringDataJaxb.OrderDto order = orderService.updateOrder(orderId, request);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<SpringDataJaxb.OrderDto> getOrder(@PathVariable Long orderId) {
        SpringDataJaxb.OrderDto order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SpringDataJaxb.OrderDto>> getOrders() {
        List<SpringDataJaxb.OrderDto> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}

