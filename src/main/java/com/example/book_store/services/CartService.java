package com.example.book_store.services;

import com.example.book_store.models.Book;
import com.example.book_store.models.Cart;
import com.example.book_store.models.User;
import com.example.book_store.repo.BookRepo;
import com.example.book_store.repo.CartRepo;
import com.example.book_store.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;

    public void addToCart(Long user_id, Long book_id, int quantity) {
        User user = userRepo.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepo.findById(book_id).orElseThrow(() -> new RuntimeException("Book not found"));
        Cart cart = cartRepo.findByUserAndBookId(user, book_id).orElse(new Cart());
    }

    public void removeFromCart(Long id, Long cartId) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        Cart cartItem = cartRepo.findById(cartId).orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (!cartItem.getUser().equals(user)) {
            throw new RuntimeException("Unauthorized access");
        }

        cartRepo.delete(cartItem);
    }

    public void clearCart(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Cart> cartItems = cartRepo.findByUser(user);
        cartRepo.deleteAll(cartItems);
    }
}
