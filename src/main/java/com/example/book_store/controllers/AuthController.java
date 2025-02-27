package com.example.book_store.controllers;

import com.example.book_store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/perform_login")
    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        if (userService.authenticateUser(username, password)) {
            return "redirect:/books"; // ✅ Redirect to dashboard after successful login
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login"; // ✅ Redirect back to login with error
        }
    }
}
