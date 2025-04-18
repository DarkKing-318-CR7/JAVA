package com.example.pickleball.util;

import com.example.pickleball.model.dto.UserDto;
import com.example.pickleball.model.entity.User;

public class MapperUtils {

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();
    }
}
