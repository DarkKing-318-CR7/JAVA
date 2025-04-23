package com.example.pickleball.Controller;

import com.example.pickleball.Repositories.OrderItemRepository;
import com.example.pickleball.Repositories.OrderRepository;
import com.example.pickleball.model.entity.Order;
import com.example.pickleball.model.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    // Trang danh sách tất cả các đơn hàng
    @GetMapping("")
    public String listOrders(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "orders/orders"; // templates/orders/orders.html
    }

    // Trang chi tiết đơn hàng theo id
    @GetMapping("/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return "redirect:/orders"; // nếu không thấy thì quay về danh sách
        }

        List<OrderItem> items = orderItemRepository.findByOrder(order);
        model.addAttribute("order", order);
        model.addAttribute("items", items);
        return "orders/detail"; // templates/orders/detail.html
    }

    // Xóa đơn hàng
    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return "redirect:/orders";
    }
}
