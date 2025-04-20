package com.example.pickleball.Controller;

import com.example.pickleball.Service.AuthService;
import com.example.pickleball.model.dto.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login"; // templates/login.html
    }


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new RegisterRequest());  // ✅ đặt đúng key: "user"
        return "auth/register"; // templates/register.html
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") RegisterRequest request,
                                  RedirectAttributes redirectAttributes) {
        authService.register(request); // ✅ đúng kiểu dữ liệu
        redirectAttributes.addFlashAttribute("success", "Đăng ký thành công! Mời bạn đăng nhập.");
        return "redirect:/login";
    }
}
