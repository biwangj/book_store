package com.example.book_store.controllers;

import com.example.book_store.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{user_id}")
    public ResponseEntity<String> addToCart(@PathVariable Long user_id,
                                            @RequestParam Long bookId,
                                            @RequestParam int quantity) {
        cartService.addToCart(user_id, bookId, quantity);
        return ResponseEntity.ok("Added to cart");
    }

}
