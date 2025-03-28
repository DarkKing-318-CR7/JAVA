package com.example.pickleball_booking.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    @GetMapping("/shop")
    public String shopPage(Model model) {
        model.addAttribute("message", "Chào mừng đến với cửa hàng Pickleball!");
        return "shop"; // Trả về shop.html trong thư mục templates
    }
}
