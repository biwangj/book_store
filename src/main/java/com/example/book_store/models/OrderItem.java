package com.example.book_store.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private int quantity;
    private double price;

    public OrderItem() {

    }

    public OrderItem(Order order, Book book, int quantity, double price) {
        this.order = order;
        this.book = book;
        this.quantity = quantity;
        this.price = price;
    }

    public double getTotalPrice() {
        return this.quantity * this.price;
    }

}
