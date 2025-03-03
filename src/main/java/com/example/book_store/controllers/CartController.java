package com.example.book_store.controllers;

import com.example.book_store.models.Cart;
import com.example.book_store.services.CartService;
import com.example.book_store.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Cart> getCartItems() {
        return cartService.getCartItems();
    }

    @PostMapping("/add/{bookId}")
    public String addToCart(@PathVariable Long bookId) {
        cartService.addToCart(bookId);
        return "redirect:/book/catalog";
    }

    @PostMapping("/update/{id}")
    public void updateCartItem(@PathVariable Long id, @RequestParam int quantity) {
        cartService.updateCartItem(id, quantity);
    }

    @PostMapping("/increase")
    public String increaseBook(@RequestParam Long bookId, Model model) {
        cartService.updateCartItem(bookId, 1); // ✅ Calls the updated method
        model.addAttribute("cart", cartService.getCartItems());
        return "redirect:/cart/view";
    }

    @PostMapping("/decrease")
    public String decreaseBook(@RequestParam Long bookId, Model model) {
        cartService.updateCartItem(bookId, -1); // ✅ Calls the updated method
        model.addAttribute("cart", cartService.getCartItems());
        return "redirect:/cart/view";
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam Long bookId, Model model) {
        cartService.removeCartItem(bookId);
        model.addAttribute("cart", cartService.getCartItems());
        return "redirect:/cart/view";
    }

    @PostMapping("/checkout")
    public String checkout(Model model) {
        orderService.checkout();
        model.addAttribute("cart", cartService.getCartItems());
        return "redirect:/cart/view";
    }

}
