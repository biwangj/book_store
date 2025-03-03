package com.example.book_store.models;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    private Date orderDate;

    // Calculate total price
    public double getTotalPrice() {
        return orderItems.stream()
                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum();
    }

    public Order() {
        this.orderDate = new Date();
    }

}
