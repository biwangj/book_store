package com.example.book_store.controllers;

import com.example.book_store.models.Cart;
import com.example.book_store.services.CartService;
import com.example.book_store.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/cart/view")
    public String cart(Model model) {
        List<Cart> cartItems = cartService.getCartItems();
        model.addAttribute("cart", cartItems);
        return "cart";
    }
}
