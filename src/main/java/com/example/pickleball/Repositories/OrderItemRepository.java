package com.example.pickleball.Repositories;

import com.example.pickleball.model.entity.Order;
import com.example.pickleball.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository; // THÊM import này
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order); // Lấy các item theo đơn hàng
}
