package com.example.parrolabs.Interface;

import com.example.parrolabs.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhone(String phone);
    boolean existsByEmail(String email);
    Optional<Customer> findByEmail(String email);
}

