package com.example.pickleball.model.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;
import com.example.pickleball.model.entity.CartItem;


public class Cart {

    private Map<Product, Integer> items = new HashMap<>();

    public void addItem(Product product, int quantity) {
        if (product == null || quantity <= 0) return;
        items.merge(product, quantity, Integer::sum);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public void clear() {
        items.clear();
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public int getTotalQuantity() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }

    public double getTotalPrice() {
        return items.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }
    // Trả về tổng tiền của giỏ hàng
    public double getTotal() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }
    public List<CartItem> getItemList() {
        List<CartItem> list = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            list.add(new CartItem(entry.getKey(), entry.getValue()));
        }
        return list;
    }






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
