package com.example.pickleball_booking.controller;

import com.example.pickleball_booking.model.AppUser ;
import com.example.pickleball_booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController {
    @Autowired
    private UserService userService;

    @GetMapping("/account")
    public String accountPage(Model model) {
        AppUser  user = userService.getCurrentUser ();
        model.addAttribute("user", user);
        return "account";
    }

    @PostMapping("/account/update")
    public String updateAccount(@RequestParam("name") String name, @RequestParam("email") String email) {
        AppUser  user = userService.getCurrentUser ();
        user.setName(name);
        user.setEmail(email);
        userService.saveUser(user);
        return "redirect:/account";
    }
}