package com.example.book_store.repo;

import com.example.book_store.models.Cart;
import com.example.book_store.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Long> {

    List<Cart> findByUser(User user);
    Optional<Cart> findByUserAndBookId(User user, Long book_id);

}
