package com.example.book_store.services;

import com.example.book_store.models.Book;
import com.example.book_store.models.Category;
import com.example.book_store.repo.BookRepo;
import com.example.book_store.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    public BookRepo bookRepo;

    @Autowired
    public CategoryRepo categoryRepo;

    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    //
    public List<Book> showBook() {
        return bookRepo.findAll();
    }

    public List<Category> getAllCategory() {
        return categoryRepo.findAll();
    }

    public List<Book> filterBook(Long id) {
        return bookRepo.findByCategoryId(id);
    }

    public List<Book> searchBook(String title) {
        return bookRepo.findByTitle(title);
    }

}
