package com.example.book_store.controllers;

import com.example.book_store.models.Book;
import com.example.book_store.models.Category;
import com.example.book_store.services.BookService;
import com.example.book_store.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/show")
    public List<Book> showBook() {
        return bookService.showBook();
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategory() {
        return bookService.getAllCategory();
    }

    @GetMapping("/category/{id}")
    public List<Book> filterBook(@PathVariable Long id) {
        return bookService.filterBook(id);
    }

    @GetMapping("/search")
    public List<Book> searchBook(@PathVariable String title) {
        return bookService.searchBook(title);
    }

    @PostMapping("/add/category")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCatagory(category);
    }
}
