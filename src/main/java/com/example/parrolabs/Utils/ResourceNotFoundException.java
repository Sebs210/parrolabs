package com.example.parrolabs.Utils;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String shippingAddress, String id, Long id1) {

    }
}

