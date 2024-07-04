package com.tuancd.grocerystoremanagement.repository;

import com.tuancd.grocerystoremanagement.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
