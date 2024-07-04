package com.tuancd.grocerystoremanagement.controller;

import com.tuancd.grocerystoremanagement.model.Product;
import com.tuancd.grocerystoremanagement.service.impl.ProductService;
import com.tuancd.grocerystoremanagement.service.impl.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public Page<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(@RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("price") Double price,
                                 @RequestParam("stockQuantity") Integer stockQuantity,
                                 @RequestParam("categoryId") Long categoryId,
                                 @RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        product.setImagePath(fileName); // Correct image path
        product.setCategory(productService.getCategoryById(categoryId));
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id,
                                 @RequestParam("name") String name,
                                 @RequestParam("description") String description,
                                 @RequestParam("price") Double price,
                                 @RequestParam("stockQuantity") Integer stockQuantity,
                                 @RequestParam("categoryId") Long categoryId,
                                 @RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        Product product = productService.getProductById(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(stockQuantity);
        product.setImagePath(fileName); // Correct image path
        product.setCategory(productService.getCategoryById(categoryId));
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
