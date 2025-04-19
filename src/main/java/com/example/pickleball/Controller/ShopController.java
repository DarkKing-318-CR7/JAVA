package com.example.pickleball.Controller;

import com.example.pickleball.model.entity.Product;
import com.example.pickleball.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "shop/shop";
    }

    @GetMapping("/detail/{id}")
    public String showProductDetail(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "shop/detail";
    }
}
