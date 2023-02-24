package com.example.parrolabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private CustomerDto customer;
    private List<OrderItemDto> items;
    private ShippingAddressDto shippingAddress;
    private BigDecimal totalPrice;
}
