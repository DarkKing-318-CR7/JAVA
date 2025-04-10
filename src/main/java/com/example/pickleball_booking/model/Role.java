package com.example.pickleball_booking.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // ví dụ: "ROLE_ADMIN", "ROLE_USER"
}