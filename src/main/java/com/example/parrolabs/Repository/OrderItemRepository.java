package com.example.parrolabs.Repository;

import com.example.parrolabs.entity.OrderItem;
import com.example.parrolabs.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    boolean existsByProduct(Product product);
}
