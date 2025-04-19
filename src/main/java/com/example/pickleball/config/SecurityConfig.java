package com.example.pickleball.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                                "/", "/index", "/login", "/register","/booking",
                                "/courts/list", "/shop/products",
                                "/css/**", "/js/**", "/images/**"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error") // ✅ thêm dòng này để khi đăng nhập sai thì redirect kèm lỗi
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

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        UserDetails user = User.builder()
                .username("admin")
                .password(encoder.encode("123")) // Mã hóa mật khẩu bằng BCrypt
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
