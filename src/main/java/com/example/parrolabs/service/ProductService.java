package com.example.parrolabs.service;

import com.example.parrolabs.Interface.OrderItemRepository;
import com.example.parrolabs.Interface.ProductRepository;
import com.example.parrolabs.Utils.ResourceNotFoundException;
import com.example.parrolabs.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        if (orderItemRepository.existsByProduct(product)) {
            throw new BusinessException("Product cannot be deleted as it is being used in an order.");
        }
        productRepository.delete(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setStock(productDetails.getStock());

        return productRepository.save(product);
    }
}

