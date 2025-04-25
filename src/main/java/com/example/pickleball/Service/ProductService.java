package com.example.pickleball.Service;

import com.example.pickleball.model.entity.Product;
import com.example.pickleball.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        // fix: handle Optional
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> findByCategory(String category) {
        return productRepository.findByCategory(category); // dùng phương thức query của Spring Data JPA
    }
}
