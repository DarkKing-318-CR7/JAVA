package com.example.pickleball.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String dashboard() {
        return "admin/admin";   // Trang dashboard.html của admin
    }
}
