package com.tuancd.grocerystoremanagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
//SaleDetail:
// Lưu trữ thông tin chi tiết về các mặt hàng trong các đơn hàng bán hàng.
@Table(name = "sale_details")
public class SaleDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    1 Hoa don - N chi tiet
    @ManyToOne
    @JoinColumn(name = "sale", nullable = false)
    private Sale sale;

//    1 khach hang - N chi tiet hoa don
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;
    private Double price;
}
