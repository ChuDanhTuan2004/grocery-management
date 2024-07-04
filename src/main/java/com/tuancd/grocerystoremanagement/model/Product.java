package com.tuancd.grocerystoremanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
//Product: Đại diện cho các sản phẩm trong cửa hàng tạp hóa.
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private Double price;

    @Column(name = "image_path")
    private String imagePath;

    private Integer stockQuantity; // Thêm trường stockQuantity

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
