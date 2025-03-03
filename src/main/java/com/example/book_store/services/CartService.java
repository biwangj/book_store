package com.example.book_store.services;

import com.example.book_store.models.Book;
import com.example.book_store.models.Cart;
import com.example.book_store.repo.BookRepo;
import com.example.book_store.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private BookRepo bookRepo;

    public List<Cart> getCartItems() {
        return cartRepo.findAll();
    }

    public void addToCart(Long bookId) {
        Optional<Book> bookOpt = bookRepo.findById(bookId);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            Cart cartItem = cartRepo.findByBook(book);
            if (cartItem != null) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
            } else {
                cartItem = new Cart(book, 1);
            }
            cartRepo.save(cartItem);
        }
    }

    public void updateCartItem(Long bookId, int quantityChange) {
        Optional<Book> bookOpt = bookRepo.findById(bookId);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            Cart cartItem = cartRepo.findByBook(book);

            if (cartItem != null) {
                int newQuantity = cartItem.getQuantity() + quantityChange;

                if (newQuantity <= 0) {
                    cartRepo.delete(cartItem);
                } else {
                    cartItem.setQuantity(newQuantity);
                    cartRepo.save(cartItem);
                }
            }
        }
    }

    public void removeCartItem(Long bookId) {
        Optional<Book> bookOpt = bookRepo.findById(bookId);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            cartRepo.deleteByBook(book); // ✅ Deletes based on book ID
        }
    }

    public void clearCart() {
        cartRepo.deleteAll();
        System.out.println("cart deleted" + cartRepo.findAll());
    }

}
