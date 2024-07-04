package com.tuancd.grocerystoremanagement.controller;

import com.tuancd.grocerystoremanagement.exception.ResourceNotFoundException;
import com.tuancd.grocerystoremanagement.model.Supplier;
import com.tuancd.grocerystoremanagement.service.impl.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Supplier supplier = supplierService.getSupplierById(id);
        if (supplier == null) {
            throw new ResourceNotFoundException("Supplier not found with id: " + id);
        }
        return ResponseEntity.ok(supplier);
    }

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody Supplier supplier) {
        Supplier savedSupplier = supplierService.saveSupplier(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @Valid @RequestBody Supplier supplierData) {
        Supplier existingSupplier = supplierService.getSupplierById(id);
        if (existingSupplier == null) {
            throw new ResourceNotFoundException("Supplier not found with id: " + id);
        }

        existingSupplier.setName(supplierData.getName());
        existingSupplier.setRating(supplierData.getRating());
        existingSupplier.setImage(supplierData.getImage());

        Supplier updatedSupplier = supplierService.saveSupplier(existingSupplier);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        Supplier existingSupplier = supplierService.getSupplierById(id);
        if (existingSupplier == null) {
            throw new ResourceNotFoundException("Supplier not found with id: " + id);
        }
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }
}
