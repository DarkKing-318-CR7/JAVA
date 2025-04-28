//package com.example.pickleball.Repositories;
//
//import com.example.pickleball.model.entity.Product;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class ProductRepository {
//    private static final List<Product> products = new ArrayList<>();
//
//    static {
//        products.add(new Product(1, "Áo bóng đá", "Áo thi đấu chất lượng cao", 200000, "/images/shirt/shirt1.png", "shirt"));
//        products.add(new Product(2, "Giày sân cỏ", "Giày đá sân cỏ nhân tạo", 350000, "/images/giay/giay1.png", "shoes"));
//        products.add(new Product(3, "Giày sân cỏ", "Giày đá sân cỏ nhân tạo", 330000, "/images/giay/giay2.png", "shoes"));
//        products.add(new Product(4, "Bóng", "Bóng chuẩn thi đấu", 190000, "/images/ball/bongchuan.png", "ball"));
//        products.add(new Product(5, "Bóng", "Bóng chuẩn thi đấu", 190000, "/images/ball/ball1.png", "ball"));
//        products.add(new Product(6, "Vợt", "Vợt pickleball", 260000, "/images/votcaulong/votcaulong.png", "racket"));
//        products.add(new Product(7, "Áo", "Áo thi đấu", 180000, "/images/shirt/shirt2.png", "shirt"));
//        products.add(new Product(8, "Vợt", "Vợt pickleball", 2100000, "/images/votcaulong/vot1.png", "racket"));
//
//
//
//    }
//
//    public List<Product> findAll() {
//        return products;
//    }
//
//    public Product findById(int id) {
//        return products.stream()
//                .filter(p -> p.getId() == id)
//                .findFirst()
//                .orElse(null);
//    }
//
//}
package com.example.pickleball.Repositories;

import com.example.pickleball.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category);
}

