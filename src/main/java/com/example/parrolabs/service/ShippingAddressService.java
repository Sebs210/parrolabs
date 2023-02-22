package com.example.parrolabs.service;

import com.example.parrolabs.Interface.ShippingAddressRepository;
import com.example.parrolabs.Utils.ResourceNotFoundException;
import com.example.parrolabs.entity.ShippingAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingAddressService {
    private final ShippingAddressRepository shippingAddressRepository;

    @Autowired
    public ShippingAddressService(ShippingAddressRepository shippingAddressRepository) {
        this.shippingAddressRepository = shippingAddressRepository;
    }

    public ShippingAddress createShippingAddress(ShippingAddress shippingAddress) {
        return shippingAddressRepository.save(shippingAddress);
    }

    public ShippingAddress getShippingAddressById(Long id) {
        return shippingAddressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ShippingAddress", "id", id));
    }

    public List<ShippingAddress> getAllShippingAddresses() {
        return shippingAddressRepository.findAll();
    }

    public ShippingAddress updateShippingAddress(Long id, ShippingAddress shippingAddressDetails) {
        ShippingAddress shippingAddress = getShippingAddressById(id);
        shippingAddress.setAddress(shippingAddressDetails.getAddress());
        shippingAddress.setCity(shippingAddressDetails.getCity());
        shippingAddress.setState(shippingAddressDetails.getState());
        shippingAddress.setCountry(shippingAddressDetails.getCountry());
        shippingAddress.setPostalCode(shippingAddressDetails.getPostalCode());
        return shippingAddressRepository.save(shippingAddress);
    }

    public void deleteShippingAddress(Long id) {
        ShippingAddress shippingAddress = getShippingAddressById(id);
        shippingAddressRepository.delete(shippingAddress);
    }
}

