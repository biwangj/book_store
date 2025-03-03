package com.example.book_store.services;

import com.example.book_store.models.Cart;
import com.example.book_store.models.Order;
import com.example.book_store.models.OrderItem;
import com.example.book_store.repo.CartRepo;
import com.example.book_store.repo.OrderItemRepo;
import com.example.book_store.repo.OrderRepo;
import com.example.book_store.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    public void checkout() {
        List<Cart> cartItems = cartRepo.findAll();

        if(!cartItems.isEmpty()) {

            Order order = new Order();
            orderRepo.save(order);

            for(Cart item : cartItems) {
                OrderItem orderItem = new OrderItem(order, item.getBook(), item.getQuantity(), item.getBook().getPrice());
                orderItemRepo.save(orderItem);
            }

            cartRepo.deleteAll();

        }else {
            System.out.println("No items in cart.");
        }
    }

    public List<Order> getOrders() {

        return orderRepo.findAll();

    }
}
