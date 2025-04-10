package com.example.pickleball_booking.service;

import com.example.pickleball_booking.model.AppUser;
import com.example.pickleball_booking.model.Role;
import com.example.pickleball_booking.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AppUser  getCurrentUser () {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return userRepository.findByUsername(username).orElse(null);
    }

    public void registerUser (AppUser  user) {
        // Kiểm tra xem email hoặc tên người dùng đã tồn tại chưa
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email đã tồn tại");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Tên người dùng đã tồn tại");
        }

        // Mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Lưu người dùng vào cơ sở dữ liệu
        userRepository.save(user);
    }

    // Tìm người dùng theo email
    public Optional<AppUser> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Tìm người dùng theo username
    public Optional<AppUser> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Kiểm tra sự tồn tại của người dùng theo email
    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Kiểm tra sự tồn tại của người dùng theo username
    public boolean userExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Cập nhật thông tin người dùng
    public AppUser updateUser(AppUser user) {
        return userRepository.save(user);
    }

    public void saveUser(AppUser user) {
        userRepository.save(user);
    }

}
