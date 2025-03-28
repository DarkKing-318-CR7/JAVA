package com.example.controller;

import com.example.model.Product;
import com.example.service.CartService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @PostMapping("/add/{id}")
    @ResponseBody
    public String addToCart(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            cartService.addToCart(product);
            return "{\"message\": \"Sản phẩm đã được thêm vào giỏ hàng!\"}";
        }
        return "{\"message\": \"Sản phẩm không tồn tại!\"}";
    }

    @GetMapping
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        return "cart";
    }
}
