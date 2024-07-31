package com.emamagic.ecommerce.customer;

import com.emamagic.ecommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository repo;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest req) {
        var customer = repo.save(mapper.toCustomer(req));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest req) {
        var customer = repo.findById(req.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provided ID:: %s", req.id())
                ));
        mergerCustomer(customer, req);
        repo.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest req) {
        if (StringUtils.isNotBlank(req.firstname())) {
            customer.setFirstname(req.firstname());
        }
        if (StringUtils.isNotBlank(req.lastname())) {
            customer.setFirstname(req.lastname());
        }
        if (StringUtils.isNotBlank(req.email())) {
            customer.setFirstname(req.email());
        }
        if (req.address() != null) {
            customer.setAddress(req.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return repo.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .toList();
    }

    public Boolean existsById(String customerId) {
        return repo.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repo.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID:: %s", customerId)));
    }

    public void deleteCustomer(String customerId) {
        repo.deleteById(customerId);
    }
}
