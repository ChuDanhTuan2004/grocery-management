package com.tuancd.grocerystoremanagement.repository;

import com.tuancd.grocerystoremanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
