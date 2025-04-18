package com.example.pickleball.Service.Impl;

import com.example.pickleball.exception.BadRequestException;
import com.example.pickleball.exception.ResourceNotFoundException;
import com.example.pickleball.model.dto.RegisterRequest;
import com.example.pickleball.model.dto.UserDto;
import com.example.pickleball.model.entity.User;
import com.example.pickleball.Repositories.UserRepository;
import com.example.pickleball.Service.UserService;
import com.example.pickleball.util.MapperUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(RegisterRequest request) {
        if (userRepository.existsByusername(request.getUsername())) {
            throw new BadRequestException("username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);
        return MapperUtils.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        return MapperUtils.mapToUserDto(user);
    }
}

