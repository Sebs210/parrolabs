package com.example.parrolabs.service;

import com.example.parrolabs.Interface.CustomerRepository;
import com.example.parrolabs.Interface.ShippingAddressRepository;
import com.example.parrolabs.Utils.ResourceNotFoundException;
import com.example.parrolabs.entity.Customer;
import com.example.parrolabs.entity.ShippingAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ShippingAddressRepository shippingAddressRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ShippingAddressRepository shippingAddressRepository) {
        this.customerRepository = customerRepository;
        this.shippingAddressRepository = shippingAddressRepository;
    }

    public Customer addCustomer(Customer customer) {
        if (customer.getPhone() != null && customerRepository.findByPhone(customer.getPhone()).isPresent()) {
            throw new IllegalArgumentException("Phone number already exists.");
        }

        if (customer.getEmail() != null && customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email address already exists.");
        }

        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        if (customer.getId() == null) {
            throw new IllegalArgumentException("Customer ID is required.");
        }

        if (customer.getPhone() != null) {
            Optional<Customer> existingCustomer = customerRepository.findByPhone(customer.getPhone());
            if (existingCustomer.isPresent() && !existingCustomer.get().getId().equals(customer.getId())) {
                throw new IllegalArgumentException("Phone number already exists.");
            }
        }

        if (customer.getEmail() != null) {
            Optional<Customer> existingCustomer = customerRepository.findByEmail(customer.getEmail());
            if (existingCustomer.isPresent() && !existingCustomer.get().getId().equals(customer.getId())) {
                throw new IllegalArgumentException("Email address already exists.");
            }
        }

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            if (!customer.get().getOrders().isEmpty()) {
                throw new IllegalArgumentException("Cannot delete customer because they have existing orders.");
            }

            customerRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Customer not found.");
        }
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found."));
    }

    public Customer findCustomerByPhone(String phone) {
        return customerRepository.findByPhone(phone).orElseThrow(() -> new ResourceNotFoundException("Customer not found."));
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public List<ShippingAddress> findShippingAddressesByCustomerId(Long id) {
        return shippingAddressRepository.findByCustomerId(id);
    }

    public ShippingAddress findShippingAddressById(Long id) {
        return shippingAddressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Shipping address not found."));
    }
}
