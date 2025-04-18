package com.example.pickleball.Service;

import com.example.pickleball.model.dto.LoginRequest;
import com.example.pickleball.model.dto.RegisterRequest;
import com.example.pickleball.model.dto.UserDto;

public interface AuthService {
    UserDto register(RegisterRequest request);
    String login(LoginRequest request); // trả về JWT token nếu dùng JWT
}
