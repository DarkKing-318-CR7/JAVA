package com.example.pickleball.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.pickleball.Repositories.OrderItemRepository;
import com.example.pickleball.Repositories.OrderRepository;
import com.example.pickleball.Repositories.ProductRepository;
import com.example.pickleball.model.entity.*;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    // Hiển thị trang checkout
    @GetMapping
    public String showCheckoutPage(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.getItems().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "⚠️ Hãy chọn sản phẩm trước khi thanh toán.");
            return "redirect:/cart";
        }

        model.addAttribute("cart", cart);
        return "checkout/checkout";
    }

    // Xử lý khi nhấn "Thanh toán"
    @PostMapping("/process")
    public String processCheckout(HttpSession session, RedirectAttributes redirectAttributes) {
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.getItems().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "⚠️ Không thể thanh toán vì giỏ hàng trống.");
            return "redirect:/cart";
        }

        // Tạo đơn hàng
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalAmount(cart.getTotal());

        // Thông tin khách hàng tạm thời
        order.setCustomerName("Khách hàng");
        order.setAddress("123 ABC");
        order.setPhone("0123456789");

        order = orderRepository.save(order); // lưu đơn

        // Tạo các item
        List<OrderItem> orderItems = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setUnitPrice(product.getPrice());

            orderItems.add(item);
        }

        orderItemRepository.saveAll(orderItems);

        session.removeAttribute("cart");

        return "redirect:/orders/confirmation";
    }
}
