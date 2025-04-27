package com.example.pickleball.Controller;

import com.example.pickleball.model.entity.Cart;
import com.example.pickleball.model.entity.Product;
import com.example.pickleball.Service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ProductService productService;

    // Hiển thị giỏ hàng
    @GetMapping
    public String viewCart(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        model.addAttribute("cart", cart);
        model.addAttribute("itemList", cart.getItemList());
        return "cart/cart";
    }

    // ⭐ Thêm sản phẩm từ TRANG CHI TIẾT (form submit)
    @PostMapping("/add")
    public String addToCartFromForm(@RequestParam("productId") int productId,
                                    @RequestParam("quantity") int quantity,
                                    HttpSession session) {
        Product product = productService.findById(productId);
        if (product == null) {
            return "redirect:/shop?error=notfound";
        }
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        cart.addItem(product, quantity);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    // ⭐ Thêm sản phẩm từ FETCH API (từ trang danh sách)
    @PostMapping("/add/{productId}")
    @ResponseBody
    public ResponseEntity<String> addToCartFromFetch(@PathVariable("productId") int productId, HttpSession session) {
        Product product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.badRequest().body("Product not found");
        }
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        cart.addItem(product, 1); // Click icon => luôn thêm 1 sản phẩm
        session.setAttribute("cart", cart);
        return ResponseEntity.ok("Added to cart successfully!");
    }

    // Xóa sản phẩm khỏi giỏ hàng
    @PostMapping("/remove")
    public String removeFromCart(@RequestParam("productId") int productId, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null) {
            Product product = productService.findById(productId);
            if (product != null) {
                cart.removeItem(product);
                session.setAttribute("cart", cart);
            }
        }
        return "redirect:/cart";
    }
}
