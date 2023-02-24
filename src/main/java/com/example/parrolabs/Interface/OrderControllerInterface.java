package com.example.parrolabs.Interface;

import com.example.parrolabs.Request.OrderRequest;
import com.example.parrolabs.Utils.EmptyOrderException;
import com.example.parrolabs.dto.OrderDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface OrderControllerInterface {

    @PostMapping
    ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest request) throws EmptyOrderException;

    @PutMapping("/{orderId}")
    ResponseEntity<OrderDto> updateOrder(@PathVariable Long orderId, @RequestBody OrderRequest request);

    @DeleteMapping("/{orderId}")
    ResponseEntity<Void> cancelOrder(@PathVariable Long orderId);

    @GetMapping("/{orderId}")
    ResponseEntity<OrderDto> getOrderById(@PathVariable Long orderId);
}