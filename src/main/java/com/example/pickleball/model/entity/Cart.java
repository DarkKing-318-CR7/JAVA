package com.example.pickleball.model.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Cart {

    private Map<Product, Integer> items = new HashMap<>();

    // Thêm sản phẩm vào giỏ hàng
    public void addItem(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            return;
        }
        items.merge(product, quantity, Integer::sum);
    }

    // Xóa một sản phẩm khỏi giỏ hàng
    public void removeItem(Product product) {
        items.remove(product);
    }

    // Xóa toàn bộ giỏ hàng
    public void clear() {
        items.clear();
    }

    // Lấy danh sách sản phẩm trong giỏ hàng
    public Map<Product, Integer> getItems() {
        return items;
    }

    // Tính tổng số lượng sản phẩm trong giỏ hàng
    public int getTotalQuantity() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }

    // Tính tổng tiền (giả sử Product có getPrice())
    public double getTotalPrice() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    // Optional: equals & hashCode nếu cần so sánh Cart
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(items, cart.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
