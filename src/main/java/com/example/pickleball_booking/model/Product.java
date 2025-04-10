package com.example.pickleball_booking.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Tên sản phẩm
    private Double price; // Giá sản phẩm
    private String description; // Mô tả sản phẩm

    @Enumerated(EnumType.STRING)
    private ProductCategory category; // Loại sản phẩm
}