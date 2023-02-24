package com.example.parrolabs.delegate;

import com.example.parrolabs.Controller.CustomerController;
import com.example.parrolabs.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CustomerControllerDelegate {

    @Autowired
    private CustomerController customerController;


    public CustomerDto getCustomerById(Long id) {
        return new CustomerDto(customerController.getCustomerById(id));
    }

    public CustomerDto registerCustomer(CustomerDto customerDto) {
        return new CustomerDto(customerController.registerCustomer(customerDto.toEntity()));
    }

    public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
        return new CustomerDto(customerController.updateCustomer(id, customerDto.toEntity()));
    }

    public void deleteCustomer(Long id) {
        customerController.deleteCustomer(id);
    }
}
