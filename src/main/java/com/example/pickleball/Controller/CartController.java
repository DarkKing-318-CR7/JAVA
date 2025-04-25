package com.example.pickleball.Controller;

import com.example.pickleball.model.entity.Cart;
import com.example.pickleball.model.entity.Product;
import com.example.pickleball.Service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("itemList", cart.getItemList()); // ⚠️ THÊM DÒNG NÀY

        return "cart/cart";
    }



    // Thêm vào giỏ hàng
    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") int productId,
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

        return "redirect:/cart"; // sau khi thêm → quay về giỏ hàng
    }
}
