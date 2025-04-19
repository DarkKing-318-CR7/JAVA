package com.example.pickleball.Controller;

import com.example.pickleball.model.dto.OrderRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @PostMapping
    public String handleCheckout(@ModelAttribute OrderRequest orderRequest, Model model) {
        // Xử lý logic với thông tin từ OrderRequest
        System.out.println("Full Name: " + orderRequest.getFullName());
        System.out.println("Address: " + orderRequest.getAddress());
        System.out.println("Phone: " + orderRequest.getPhone());

        // Gửi dữ liệu sang view nếu cần
        model.addAttribute("order", orderRequest);

        // Trả về tên trang view sau khi xử lý (ví dụ: checkout-success.html)
        return "checkout-success";
    }
}
