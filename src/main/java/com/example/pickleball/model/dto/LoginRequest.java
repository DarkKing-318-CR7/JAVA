package com.example.pickleball.model.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String email;
    private String password;

    // Getters, Setters
}
