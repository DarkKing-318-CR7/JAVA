package com.example.pickleball.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "support_requests")
@Data
public class SupportRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;

    private String email;

    private String subject;

    @Column(length = 2000) // Nội dung có thể dài
    private String message;
}
