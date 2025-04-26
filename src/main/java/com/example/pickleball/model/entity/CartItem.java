package com.example.pickleball.model.entity;
import com.example.pickleball.model.entity.Product;
import lombok.Data;
@Data


public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

}
