package com.ecommerce.order.controllers;


import com.ecommerce.order.models.CartItem;
import com.ecommerce.order.dtos.CartItemRequest;
import com.ecommerce.order.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private  final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    //Add product to cart


    @PostMapping
    public ResponseEntity<String> addProductToCart(@RequestHeader("X-User-Id") Long userId,
                                                @RequestBody CartItemRequest request) {

        if(!cartService.addToCart(userId, request))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add item to cart OR User not found or product not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> deleteItemFromCart(@RequestHeader("X-User-Id") Long userId,
                                                     @PathVariable Long productId) {

        boolean deleted=cartService.deleteItemFromCart(userId, productId);
     return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }

    @GetMapping("/items")
    public ResponseEntity<List<CartItem>> getCart(@RequestHeader("X-User-Id") Long userId) {
        // Logic to retrieve cart items for the user
        List<CartItem> cartItems = cartService.getCart(userId);
        return ResponseEntity.ok(cartItems);
    }


}
