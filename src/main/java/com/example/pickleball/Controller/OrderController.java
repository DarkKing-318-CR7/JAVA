package com.example.pickleball.Controller;

import com.example.pickleball.model.entity.Cart;
import com.example.pickleball.Service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout/order")
    public String processCheckout(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }

        // Tạm hardcode thông tin khách hàng
        String customerName = "Test User";
        String address = "123 Main Street";
        String phone = "0123456789";

        orderService.createOrderFromCart(cart, customerName, address, phone);

        session.removeAttribute("cart");
        return "redirect:/checkout-success";
    }

    @GetMapping("/checkout-success")
    public String showSuccessPage() {
        return "checkout-success";
    }
}
