
package com.example.pickleball_booking.service;

import com.example.pickleball_booking.model.Product;
import com.example.pickleball_booking.model.ShopItem;
import com.example.pickleball_booking.repository.ProductRepository;
import com.example.pickleball_booking.repository.ShopItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopItemService {

    @Autowired
    private ShopItemRepository shopItemRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<ShopItem> getAllShopItems() {
        return shopItemRepository.findAll();
    }

    public Optional<ShopItem> getShopItemById(Long id) {
        return shopItemRepository.findById(id);
    }

    public ShopItem saveShopItem(ShopItem shopItem) {
        return shopItemRepository.save(shopItem);
    }

    public void deleteShopItem(Long id) {
        shopItemRepository.deleteById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}