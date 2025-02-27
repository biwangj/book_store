package com.example.book_store.repo;

import com.example.book_store.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

    //filter
    List<Book> findByCategoryId(Long id);
    //search
    List<Book> findByTitle(String title);
}
