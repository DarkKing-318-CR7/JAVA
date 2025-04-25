package com.example.pickleball.Controller;

import com.example.pickleball.Repositories.OrderItemRepository;
import com.example.pickleball.Repositories.OrderRepository;
import com.example.pickleball.model.entity.Order;
import com.example.pickleball.model.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Trang danh sách đơn hàng
    @GetMapping("")
    public String listOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders/orders";
    }

    // Trang chi tiết đơn hàng
    @GetMapping("/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return "redirect:/orders";
        }

        List<OrderItem> items = orderItemRepository.findByOrder(order);

        // Tính tổng tiền
        BigDecimal total = items.stream()
                .map(item -> BigDecimal.valueOf(item.getProduct().getPrice())
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        DecimalFormat df = new DecimalFormat("#,###");

        // Format tổng tiền
        String totalFormatted = df.format(total) + " đ";

        // Format từng sản phẩm
        List<String> formattedPrices = new ArrayList<>();
        for (OrderItem item : items) {
            BigDecimal itemTotal = BigDecimal.valueOf(item.getProduct().getPrice())
                    .multiply(BigDecimal.valueOf(item.getQuantity()));
            formattedPrices.add(df.format(itemTotal) + " đ");
        }

        model.addAttribute("order", order);
        model.addAttribute("items", items);
        model.addAttribute("totalPrice", total);
        model.addAttribute("totalPriceFormatted", totalFormatted);
        model.addAttribute("formattedPrices", formattedPrices);

        return "orders/detail";
    }

    // ✅ Đánh dấu đã nhận hàng
    @PostMapping("/{id}/receive")
    public String markAsReceived(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus("RECEIVED");
            orderRepository.save(order);
        }
        return "redirect:/orders/" + id;
    }

    // Xóa đơn hàng
    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "redirect:/orders";
    }

    // Trang xác nhận đặt hàng thành công
    @GetMapping("/confirmation")
    public String showConfirmationPage() {
        return "checkout-success";
    }
}
