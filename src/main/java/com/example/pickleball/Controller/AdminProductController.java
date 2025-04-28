package com.example.pickleball.Controller;

import com.example.pickleball.Service.ProductService;
import com.example.pickleball.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String viewProductsPage(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "admin/admin-product";  // ⚡ File HTML đang là admin-product.html
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/api")
    @ResponseBody
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
