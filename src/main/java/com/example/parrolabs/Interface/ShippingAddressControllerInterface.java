package com.example.parrolabs.Interface;

import com.example.parrolabs.dto.ShippingAddressDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/shipping-addresses")
public interface ShippingAddressControllerInterface {

    @GetMapping
    ResponseEntity<List<ShippingAddressDto>> getAllShippingAddresses();

    @GetMapping("/{id}")
    ResponseEntity<ShippingAddressDto> getShippingAddressById(@PathVariable Long id);

    @PostMapping
    ResponseEntity<ShippingAddressDto> createShippingAddress(@RequestBody ShippingAddressDto shippingAddressDto);

    @PutMapping("/{id}")
    ResponseEntity<ShippingAddressDto> updateShippingAddress(@PathVariable Long id,
                                                             @RequestBody ShippingAddressDto shippingAddressDto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteShippingAddress(@PathVariable Long id);
}

