package com.example.parrolabs.delegate;

import com.example.parrolabs.entity.Customer;
import com.example.parrolabs.entity.CustomerDto;
import com.example.parrolabs.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerDelegate {

    @Autowired
    private CustomerService customerService;

    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream()
                .map(customer -> new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone()))
                .collect(Collectors.toList());
    }

    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerService.getCustomerById(id);
        return new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone());
    }

    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer(customerDto.getName(), customerDto.getEmail(), customerDto.getPhone());
        customer = customerService.createCustomer(customer);
        return new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone());
    }

    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = new Customer(customerDto.getName(), customerDto.getEmail(), customerDto.getPhone());
        customer.setId(id);
        customer = customerService.updateCustomer(id, customer);
        return new CustomerDto(customer.getId(), customer.getName(), customer.getEmail(), customer.getPhone());
    }

    public void deleteCustomer(Long id) {
        customerService.deleteCustomer(id);
    }
}
