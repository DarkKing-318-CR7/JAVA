package com.example.pickleball.Service;

import com.example.pickleball.model.entity.Cart;
import com.example.pickleball.model.entity.Order;
import com.example.pickleball.model.entity.OrderItem;
import com.example.pickleball.model.entity.Product;
import com.example.pickleball.Repositories.OrderRepository;
import com.example.pickleball.Repositories.OrderItemRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order createOrderFromCart(Cart cart, String customerName, String address, String phone) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setAddress(address);
        order.setPhone(phone);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(quantity);
            orderItem.setUnitPrice(product.getPrice()); // ✅
            orderItem.setOrder(order);

            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotalAmount(cart.getTotalPrice());

        // Save order and order items
        orderRepository.save(order);
        for (OrderItem item : orderItems) {
            orderItemRepository.save(item);
        }

        return order;
    }
}
