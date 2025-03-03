package com.example.book_store.controllers;

import com.example.book_store.repo.UserRepo;
import com.example.book_store.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    public UserRepo userRepo;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        boolean isAuthenticated = userService.authenticateUser(username, password);

        if (isAuthenticated) {
            System.out.println("Success");//test
            return "redirect:/books"; //procceeds when success
        } else {
            System.out.println("Fail");//test
            redirectAttributes.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login"; //back to login
        }
    }
}
