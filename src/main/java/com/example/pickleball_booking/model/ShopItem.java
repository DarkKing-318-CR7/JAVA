package com.example.pickleball_booking.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shop_items")
@Data // Hoặc dùng @Getter, @Setter nếu không dùng Lombok @Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private int quantity;
}
