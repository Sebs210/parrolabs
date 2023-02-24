package com.example.parrolabs.Request;

import com.example.parrolabs.entity.Order;
import com.example.parrolabs.entity.OrderItem;
import com.example.parrolabs.entity.ShippingAddress;

import java.util.List;

public class OrderRequest extends Order {
    private Long customerId;
    private Long shippingAddressId;
    private List<OrderItem> orderItems;

    private ShippingAddress shippingAddress;



    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.setShippingAddress(shippingAddress);
    }

    public Object getTotalValue() {
        return null;
    }
}
