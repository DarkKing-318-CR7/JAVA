package com.example.pickleball.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homePage() {
        return "index"; // Trả về trang chủ
    }
    @GetMapping("/support")
    public String supportPage() {
        return "support/support"; // đúng với đường dẫn bạn đã cho ở ảnh
    }
}
