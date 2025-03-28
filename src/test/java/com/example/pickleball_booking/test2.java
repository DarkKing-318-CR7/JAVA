package com.example.pickleball_booking;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class test2 {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = "$2a$10$KS.7cxVrf2S8w.IXsxYsXO45gkC6KpYmDJDLT7VrBjjLTejEeCEYe"; // Mật khẩu từ database
        System.out.println(encoder.matches("1234", hashedPassword)); // Phải in ra "true"
    }
}
