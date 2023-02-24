package com.example.parrolabs.Interface;

import com.example.parrolabs.dto.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface CustomerControllerInterface {

    @GetMapping("/{id}")
    ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id);

    @GetMapping("")
    ResponseEntity<List<CustomerDto>> getAllCustomers();

    @PostMapping("/register")
    ResponseEntity<CustomerDto> registerCustomer(@RequestBody CustomerDto customerDto);

    @PutMapping("/{id}")
    ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteCustomer(@PathVariable Long id);

}