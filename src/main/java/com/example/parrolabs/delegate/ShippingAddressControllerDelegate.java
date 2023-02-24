package com.example.parrolabs.delegate;
import com.example.parrolabs.dto.ShippingAddressDto;
import com.example.parrolabs.entity.ShippingAddress;
import com.example.parrolabs.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShippingAddressControllerDelegate {

    @Autowired
    private ShippingAddressService shippingAddressService;

    public List<ShippingAddress> getAllShippingAddresses() {
        return shippingAddressService.getAllShippingAddresses();
    }

    public ShippingAddress getShippingAddressById(Long id) {
        return shippingAddressService.getShippingAddressById(id);
    }

    public ShippingAddress createShippingAddress(ShippingAddressDto shippingAddressDto) {
        return shippingAddressService.createShippingAddress(shippingAddressDto);
    }

    public ShippingAddress updateShippingAddress(Long id, ShippingAddressDto shippingAddressDto) {
        return shippingAddressService.updateShippingAddress(id, shippingAddressDto);
    }

    public void deleteShippingAddress(Long id) {
        shippingAddressService.deleteShippingAddress(id);
    }
}

