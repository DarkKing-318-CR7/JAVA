package com.example.pickleball.model.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String fullName;
    private String email;
    private String phone;
    private String password;
    private String Username;


    // Getters, Setters
}