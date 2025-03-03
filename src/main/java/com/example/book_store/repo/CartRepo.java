package com.example.book_store.repo;

import com.example.book_store.models.Book;
import com.example.book_store.models.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {

    Cart findByBook(Book book);

    @Transactional
    void deleteByBook(Book book);
}
