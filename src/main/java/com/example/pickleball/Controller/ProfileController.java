package com.example.pickleball.Controller;

import ch.qos.logback.core.model.Model;
import com.example.pickleball.Repositories.UserRepository;
import com.example.pickleball.Service.UserService;
import com.example.pickleball.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String viewProfile(org.springframework.ui.Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "profile/profile";
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute User userForm, Principal principal) {
        userService.updateUserInfo(principal.getName(), userForm);
        return "redirect:/profile";
    }
}
