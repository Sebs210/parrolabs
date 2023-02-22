package com.example.parrolabs.Request;

import java.util.List;

public class OrderRequest {
    private Long customerId;
    private Long shippingAddressId;
    private List<OrderItemRequest> orderItems;

    public OrderRequest(Long customerId, Long shippingAddressId, List<OrderItemRequest> orderItems) {
        this.customerId = customerId;
        this.shippingAddressId = shippingAddressId;
        this.orderItems = orderItems;
    }

    public OrderRequest() {}


}
