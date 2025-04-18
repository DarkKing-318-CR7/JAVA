package com.example.pickleball.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF để tránh lỗi với form login thủ công
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/", "/index", "/login", "/register",
                                "/courts/list", "/shop/products",
                                "/css/**", "/js/**", "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")                // Trang login tùy chỉnh
                        .loginProcessingUrl("/login")       // URL mà Spring Security sẽ xử lý POST login
                        .defaultSuccessUrl("/", true)       // Chuyển hướng sau khi login thành công
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")               // URL logout
                        .logoutSuccessUrl("/login?logout")  // Sau khi logout thì chuyển về /login
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // dùng để mã hóa mật khẩu
    }
}
