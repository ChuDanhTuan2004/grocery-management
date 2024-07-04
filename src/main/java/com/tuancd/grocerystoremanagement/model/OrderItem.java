package com.tuancd.grocerystoremanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
//OrderItem:
// Lưu trữ thông tin chi tiết về các mặt hàng trong các đơn đặt hàng.
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;
    private Double price;
}
