package com.example.pickleball.Controller;

import com.example.pickleball.Service.UserService;
import com.example.pickleball.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/change-password")
    public String showChangePasswordForm() {
        return "profile/change-password";
    }
    @PostMapping("/change-password")
    public String changePassword(@RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String confirmPassword,
                                 Principal principal) {
        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("Mật khẩu mới và xác nhận không khớp");
        }
        userService.changePassword(principal.getName(), oldPassword, newPassword);
        return "redirect:/profile?passwordChanged";
    }
}
