package com.example.pickleball_booking.config;

import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.pickleball_booking.repository")
public class DatabaseConfig {
    // Có thể thêm các cấu hình DataSource và EntityManagerFactory ở đây nếu cần.
//    @Bean
//    public DataSource dataSource() {
//        // Cấu hình DataSource tại đây
//        return DataSourceBuilder.create()
//                .url("jdbc:mysql://localhost:3306/your_database")
//                .username("your_username")
//                .password("your_password")
//                .driverClassName("com.mysql.cj.jdbc.Driver")
//                .build();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("com.example.pickleball_booking.model")
//                .persistenceUnit("your_persistence_unit")
//                .build();
//    }
//}
}