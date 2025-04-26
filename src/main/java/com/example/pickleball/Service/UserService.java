package com.example.pickleball.Service;

import com.example.pickleball.model.dto.RegisterRequest;
import com.example.pickleball.model.dto.UserDto;
import com.example.pickleball.model.entity.User;

import java.util.List;

public interface UserService {
    UserDto register(RegisterRequest request);
    UserDto getUserById(Long id);
    Long getUserIdByUsername(String username);

    User findByUsername(String username);
    void updateUserInfo(String username, User updatedUser);
    void changePassword(String username, String oldPassword, String newPassword);
    List<UserDto> getAllUsers();
    void deleteUserById(Long id);


}