package com.example.parrolabs.dto;

import com.example.parrolabs.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private List<ShippingAddressDto> shippingAddresses;

    public CustomerDto(ResponseEntity<Customer> registerCustomer) {

    }

    public Customer toEntity() {
        Customer customer = new Customer();
        customer.setId(this.id);
        customer.setName(this.name);
        customer.setEmail(this.email);
        customer.setPhone(this.phone);
        return customer;

    }
}

