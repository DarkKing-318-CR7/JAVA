package com.example.pickleball_booking.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate; // Ngày đặt hàng
    private Double totalPrice; // Tổng tiền

    @ManyToOne
    private AppUser  user; // Người dùng thực hiện đơn hàng

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems; // Các sản phẩm trong đơn hàng
}