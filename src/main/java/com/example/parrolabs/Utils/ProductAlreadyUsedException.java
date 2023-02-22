package com.example.parrolabs.Utils;

public class ProductAlreadyUsedException extends RuntimeException {
    public ProductAlreadyUsedException(String message) {
        super(message);
    }
}
