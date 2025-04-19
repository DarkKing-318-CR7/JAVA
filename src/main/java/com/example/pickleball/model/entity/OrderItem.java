package com.example.pickleball.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Sản phẩm được đặt
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Số lượng đặt
    private int quantity;

    // Giá tại thời điểm đặt hàng (có thể thay đổi theo thời gian)
    private double price;

    // Liên kết với Order (một Order có nhiều OrderItem)
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
