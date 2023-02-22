package com.example.parrolabs.service;

import com.example.parrolabs.Interface.CustomerRepository;
import com.example.parrolabs.Interface.ShippingAddressRepository;
import com.example.parrolabs.Utils.ResourceNotFoundException;
import com.example.parrolabs.entity.Customer;
import com.example.parrolabs.entity.ShippingAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
    }

    public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        customerRepository.delete(customer);
    }

    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        // Update the customer's name, email and phone number
        customer.setName(updatedCustomer.getName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhone(updatedCustomer.getPhone());

        // Update the customer's shipping addresses
        List<ShippingAddress> updatedShippingAddresses = updatedCustomer.getShippingAddresses();
        List<ShippingAddress> shippingAddresses = new ArrayList<>();

        for (ShippingAddress updatedShippingAddress : updatedShippingAddresses) {
            Optional<ShippingAddress> shippingAddressOptional = customer.getShippingAddressById(updatedShippingAddress.getId());

            if (shippingAddressOptional.isPresent()) {
                // Update the existing shipping address
                ShippingAddress shippingAddress = shippingAddressOptional.get();
                shippingAddress.setCity(updatedShippingAddress.getCity());
                shippingAddress.setState(updatedShippingAddress.getState());
                shippingAddress.setZipCode(updatedShippingAddress.getZipCode());
                shippingAddress.setCountry(updatedShippingAddress.getCountry());

                shippingAddresses.add(shippingAddress);
            } else {
                // Add a new shipping address
                ShippingAddress newShippingAddress = new ShippingAddress();
                newShippingAddress.setStreetAddress(updatedShippingAddress.getStreetAddress());
                newShippingAddress.setCity(updatedShippingAddress.getCity());
                newShippingAddress.setState(updatedShippingAddress.getState());
                newShippingAddress.setZipCode(updatedShippingAddress.getZipCode());
                newShippingAddress.setCountry(updatedShippingAddress.getCountry());
                newShippingAddress.setCustomer(customer);

                shippingAddresses.add(newShippingAddress);
            }
        }

        customer.setShippingAddresses(shippingAddresses);

        // Save the updated customer
        return customerRepository.save(customer);
    }
}

