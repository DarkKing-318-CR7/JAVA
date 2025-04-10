package com.example.pickleball_booking.repository;

import com.example.pickleball_booking.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}