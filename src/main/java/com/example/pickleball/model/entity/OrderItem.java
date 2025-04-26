package com.example.pickleball.model.entity;

import jakarta.persistence.*;

import lombok.Data;
@Data
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    @Column(name = "unit_price") // Ánh xạ đúng cột trong DB
    private double unitPrice;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}