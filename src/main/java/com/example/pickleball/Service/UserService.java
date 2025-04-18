package com.example.pickleball.Service;

import com.example.pickleball.model.dto.RegisterRequest;
import com.example.pickleball.model.dto.UserDto;

public interface UserService {
    UserDto register(RegisterRequest request);
    UserDto getUserById(Long id);
}