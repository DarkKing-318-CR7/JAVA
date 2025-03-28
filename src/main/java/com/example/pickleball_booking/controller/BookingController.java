package com.example.pickleball_booking.controller;

import com.example.pickleball_booking.model.AppUser;
import com.example.pickleball_booking.service.BookingService;
import com.example.pickleball_booking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class BookingController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Trả về trang đăng nhập
    }


    @GetMapping("/account")
    public String account(Model model, Principal principal) {
        if (principal == null) {
            System.out.println("Chưa đăng nhập!");
            return "redirect:/login";
        }

        String username = principal.getName();
        System.out.println("Đang lấy thông tin user: " + username);

        AppUser user = userService.findByUsername(username);
        if (user == null) {
            System.out.println("Không tìm thấy user!");
            return "redirect:/login";
        }

        System.out.println("User tìm thấy: " + user.getEmail());

        model.addAttribute("email", user.getEmail());
        model.addAttribute("phone", user.getPhone());
        model.addAttribute("bookings", bookingService.getBookingsByUser(user));

        return "account";
    }



    @GetMapping("/booking_form")
    public String bookingForm() {
        return "booking_form";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }
}
