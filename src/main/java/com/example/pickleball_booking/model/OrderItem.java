package com.example.pickleball_booking.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity; // Số lượng sản phẩm

    @ManyToOne
    private Order order; // Đơn hàng mà sản phẩm thuộc về

    @ManyToOne
    private Product product; // Sản phẩm trong đơn hàng
}