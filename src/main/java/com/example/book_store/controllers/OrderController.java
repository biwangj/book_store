package com.example.book_store.controllers;

import com.example.book_store.models.Order;
import com.example.book_store.repo.OrderRepo;
import com.example.book_store.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepo orderRepo;

    @GetMapping("/orders")
    public String viewOrders(Model model) {
        List<Order> orders = orderService.getOrders();

        //log for checking
        if(orders.isEmpty()) {
            System.out.println("No orders to display");
        }else {
            System.out.println("Total of: " + orders.size() + " orders.");
        }

        model.addAttribute("orders", orders);
        return "orders";
    }
}
