package com.tuancd.grocerystoremanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
//PurchaseOrderDetail:
// Lưu trữ thông tin chi tiết về các mặt hàng trong các đơn đặt hàng.
@Table(name = "purchase_order_details")
public class PurchaseOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;
    private Double price;
}