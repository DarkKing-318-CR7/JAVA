package com.example.service;

import com.example.model.CartItem;
import com.example.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final List<CartItem> cartItems = new ArrayList<>();

    public void addToCart(Product product) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        cartItems.add(new CartItem(product, 1));
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }
}
