package com.example.book_store.controllers;

import com.example.book_store.models.Category;
import org.springframework.ui.Model;
import com.example.book_store.models.Book;
import com.example.book_store.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DisplayBookController {

    @Autowired
    private BookService bookService;

    //display all books
//    @GetMapping("/book/catalog")
//    public String listBooks(Model model) {
//        List<Book> books = bookService.showBook();
//        model.addAttribute("books", books);
//        return "books";  //return to books page
//    }

    @GetMapping("/book/catalog")
    public String showBooks(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "category", required = false) Long categoryId,
            Model model) {

        List<Book> books;

        //search books in search bar
        if (search != null && !search.isEmpty()) {
            books = bookService.searchBook(search);
        }
        //filter books by category
        else if (categoryId != null) {
            books = bookService.filterBook(categoryId);
        }
        //show books
        else {
            books = bookService.showBook();
        }

        List<Category> categoryList = bookService.getAllCategory();

        model.addAttribute("books", books);
        model.addAttribute("categories", categoryList);
        model.addAttribute("selectedCategory", categoryId);

        return "books"; //return to book page
    }
}
