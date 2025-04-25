package com.example.pickleball.Controller;

import com.example.pickleball.model.entity.Product;
import com.example.pickleball.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ProductService productService;

    // Trang danh sách sản phẩm có lọc + sắp xếp
    @GetMapping("")
    public String showAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sort,
            Model model) {

        List<Product> productList;

        if (category != null && !category.equals("all")) {
            productList = new java.util.ArrayList<>(productService.findByCategory(category));
        } else {
            productList = new java.util.ArrayList<>(productService.getAll());
        }

        if ("price-asc".equals(sort)) {
            productList.sort(Comparator.comparingDouble(Product::getPrice));
        } else if ("price-desc".equals(sort)) {
            productList.sort(Comparator.comparingDouble(Product::getPrice).reversed());
        }

        model.addAttribute("products", productList);
        model.addAttribute("category", category);
        model.addAttribute("sort", sort);
        return "shop/shop";
    }


    // Trang chi tiết sản phẩm
    @GetMapping("/detail/{id}")
    public String showProductDetail(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "shop/detail";
    }
}
