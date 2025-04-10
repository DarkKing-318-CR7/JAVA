package com.example.pickleball_booking.controller;

import com.example.pickleball_booking.model.Product;
import com.example.pickleball_booking.service.ShopItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {
    @Autowired
    private ShopItemService shopItemService;

    @GetMapping("/shop")
    public String shopPage(Model model) {
        model.addAttribute("products", shopItemService.getAllProducts());
        return "shop";
    }
}