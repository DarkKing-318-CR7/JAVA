package com.example.pickleball.Repositories;

import com.example.pickleball.model.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {
    private static final List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "Áo bóng đá", "Áo thi đấu chất lượng cao", 200000));
        products.add(new Product(2, "Giày sân cỏ", "Giày đá sân cỏ nhân tạo", 350000));
        products.add(new Product(3, "Bóng", "Bóng chuẩn thi đấu", 180000));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findById(int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
