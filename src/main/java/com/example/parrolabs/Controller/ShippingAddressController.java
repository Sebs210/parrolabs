package com.example.parrolabs.Controller;

import com.example.parrolabs.dto.ShippingAddressDto;
import com.example.parrolabs.entity.ShippingAddress;
import com.example.parrolabs.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipping-addresses")
public class ShippingAddressController {

    @Autowired
    private ShippingAddressService shippingAddressService;

    @GetMapping("/{id}")
    public ResponseEntity<ShippingAddress> getShippingAddressById(@PathVariable Long id) {
        ShippingAddress shippingAddress = shippingAddressService.getShippingAddressById(id);
        return ResponseEntity.ok(shippingAddress);
    }

    @PostMapping
    public ResponseEntity<ShippingAddress> createShippingAddress(@RequestBody ShippingAddressDto shippingAddressDto) {
        ShippingAddress createdShippingAddress = shippingAddressService.createShippingAddress(shippingAddressDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShippingAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShippingAddress> updateShippingAddress(@PathVariable Long id, @RequestBody ShippingAddressDto shippingAddressDto) {
        ShippingAddress updatedShippingAddress = shippingAddressService.updateShippingAddress(id, shippingAddressDto);
        return ResponseEntity.ok(updatedShippingAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShippingAddress(@PathVariable Long id) {
        shippingAddressService.deleteShippingAddress(id);
        return ResponseEntity.noContent().build();
    }
}
