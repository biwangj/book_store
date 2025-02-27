package com.example.book_store.services;

import com.example.book_store.models.Category;
import com.example.book_store.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    public CategoryRepo categoryRepo;

    public Category addCatagory(Category category) {
        return categoryRepo.save(category);
    }
}
