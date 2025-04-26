package com.example.pickleball.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
@Entity
@Table(name = "`orders`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name") // ✅ Đúng cột
    private String customerName;

    private String address;
    private String phone;
    private String username;
    private String status;
    private String customerEmail;

    private LocalDateTime createdAt;
    private LocalDateTime orderDate;
    private double totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<>();

    // === GETTER / SETTER ===

    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }

}
