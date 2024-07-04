package com.tuancd.grocerystoremanagement.controller;

import com.tuancd.grocerystoremanagement.exception.ResourceNotFoundException;
import com.tuancd.grocerystoremanagement.model.Customer;
import com.tuancd.grocerystoremanagement.service.impl.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customerData) {
        Customer existingCustomer = customerService.getCustomerById(id);
        if (existingCustomer == null) {
            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }

        existingCustomer.setFirstName(customerData.getFirstName());
        existingCustomer.setLastName(customerData.getLastName());
        existingCustomer.setEmail(customerData.getEmail());
        existingCustomer.setPhoneNumber(customerData.getPhoneNumber());
        existingCustomer.setImage(customerData.getImage());

        Customer updatedCustomer = customerService.saveCustomer(existingCustomer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        Customer existingCustomer = customerService.getCustomerById(id);
        if (existingCustomer == null) {
            throw new ResourceNotFoundException("Customer not found with id: " + id);
        }
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
