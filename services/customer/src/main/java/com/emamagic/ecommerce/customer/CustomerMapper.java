package com.emamagic.ecommerce.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest req) {
        if (req == null) return null;

        return Customer.builder()
                .id(req.id())
                .firstname(req.firstname())
                .lastname(req.lastname())
                .email(req.email())
                .address(req.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
