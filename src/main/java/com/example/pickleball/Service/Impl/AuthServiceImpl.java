package com.example.pickleball.Service.Impl;

import com.example.pickleball.model.dto.LoginRequest;
import com.example.pickleball.model.dto.RegisterRequest;
import com.example.pickleball.model.dto.UserDto;
import com.example.pickleball.model.entity.Role;
import com.example.pickleball.model.entity.User;
import com.example.pickleball.Repositories.UserRepository;
import com.example.pickleball.Service.AuthService;
import com.example.pickleball.util.MapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhone(request.getPhone()); // ✅ Đừng quên dòng này!
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(Role.USER)); // ✅ Gán Role.USER
        user.setEnabled(true); // ✅ Cho phép đăng nhập

        userRepository.save(user);
        return MapperUtils.mapToUserDto(user);
    }

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return "Login thành công! (chưa tích hợp JWT)";
    }
}
