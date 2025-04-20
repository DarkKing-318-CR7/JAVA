package com.example.pickleball.Service;

import com.example.pickleball.Repositories.UserRepository;
import com.example.pickleball.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // mật khẩu đã mã hóa
                .roles(user.getRoles().stream().map(Enum::name).toArray(String[]::new)) // ⚠️ Lấy roles từ entity
                .disabled(!user.isEnabled()) // ⚠️ Nếu tài khoản bị disabled thì không cho login
                .build();
    }
}
