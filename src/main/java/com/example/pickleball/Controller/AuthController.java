package com.example.pickleball.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login"; // phải ghi rõ đường dẫn từ templates/
    }

    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }
}

