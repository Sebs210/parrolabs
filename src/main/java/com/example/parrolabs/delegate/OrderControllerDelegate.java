package com.example.parrolabs.delegate;


import com.example.parrolabs.Request.OrderRequest;
import com.example.parrolabs.Utils.EmptyOrderException;
import com.example.parrolabs.dto.OrderDto;
import org.springframework.http.ResponseEntity;



public interface OrderControllerDelegate {

    ResponseEntity<OrderDto> createOrder(OrderRequest request) throws EmptyOrderException;

    ResponseEntity<OrderDto> updateOrder(Long orderId, OrderRequest request);

    ResponseEntity<Void> cancelOrder(Long orderId);

    ResponseEntity<OrderDto> getOrderById(Long orderId);
}
