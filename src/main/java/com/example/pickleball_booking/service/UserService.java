package com.example.pickleball_booking.service;

import com.example.pickleball_booking.model.AppUser;
import com.example.pickleball_booking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Tìm user theo username
     */
    public AppUser findByUsername(String username) {
        AppUser user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("❌ Không tìm thấy user với username: " + username);
        } else {
            System.out.println("✅ Tìm thấy user: " + user.getUsername());
        }
        return user;
    }

    /**
     * Tìm user theo ID
     */
    public Optional<AppUser> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Lưu user mới (tự động mã hóa mật khẩu)
     */
    public AppUser saveUser(AppUser user) {
        // Kiểm tra nếu mật khẩu chưa được mã hóa thì mới mã hóa
        if (!user.getPassword().startsWith("$2a$")) { // "$2a$" là tiền tố của BCrypt
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    /**
     * Cập nhật thông tin user (nếu mật khẩu mới được nhập, thì mã hóa lại)
     */
    public AppUser updateUser(Long id, AppUser updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());

            // Nếu có mật khẩu mới thì mã hóa lại
            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Xóa user theo ID
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
