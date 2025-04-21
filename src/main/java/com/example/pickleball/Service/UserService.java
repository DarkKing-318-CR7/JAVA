package com.example.pickleball.Service;

import com.example.pickleball.model.dto.RegisterRequest;
import com.example.pickleball.model.dto.UserDto;
import com.example.pickleball.model.entity.User;

public interface UserService {
    UserDto register(RegisterRequest request);
    UserDto getUserById(Long id);
    Long getUserIdByUsername(String username);

    User findByUsername(String username);
    void updateUserInfo(String username, User updatedUser);
}