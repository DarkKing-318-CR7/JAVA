package com.example.pickleball_booking.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.pickleball_booking.repository")
public class DatabaseConfig {
    // Có thể thêm các cấu hình DataSource và EntityManagerFactory ở đây nếu cần.
}