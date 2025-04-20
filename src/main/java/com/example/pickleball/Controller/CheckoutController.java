package com.example.pickleball.Controller;

import java.util.Map;
import com.example.pickleball.model.entity.*;
import com.example.pickleball.Repositories.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String showCheckoutPage(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        return "checkout/checkout";
    }

    @PostMapping("/process")
    public String processCheckout(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/checkout";
        }

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(cart.getTotal());

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


        order.setItems(orderItems);

        orderRepository.save(order);
        orderItemRepository.saveAll(orderItems);

        session.removeAttribute("cart");

        return "redirect:/order/confirmation";
    }
}
