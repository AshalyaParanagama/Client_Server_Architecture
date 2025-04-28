/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.model;

import java.util.List;
import com.coursework.bookstore.api.util.DataInitializer;

public final class Order {
    private int id;
    private int customerId;
    private List<CartItem> items;
    private double total;
    
    // Constructor required for deserialization
    public Order() {
        // Required for JSON deserialization
    }

    // Constructor
    public Order(int id, int customerId, List<CartItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.total = calculateTotal(DataInitializer.getBooks());  // Calculate total on creation using available books
    }

    // Method to calculate the total price of the order
    public double calculateTotal(List<Book> allBooks) {
        double total = 0.0;
        for (CartItem item : items) {
            // Find the book by ID from the available books list
            Book book = findBookById(item.getBookId(), allBooks);
            if (book != null) {
                total += book.getPrice() * item.getQuantity();  // Add price * quantity
            }
        }
        return total;
    }

    private Book findBookById(int bookId, List<Book> allBooks) {
        // Logic to find the book by ID from the provided list of books
        for (Book book : allBooks) {
            if (book.getId() == bookId) {
                return book; // Return the book if it matches the ID
            }
        }
        return null; // Return null if no book matches the ID
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
        this.total = calculateTotal(DataInitializer.getBooks());  // Recalculate total after updating items
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // Override toString for easy debugging/logging
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", total=" + total +
                '}';
    }

    // Override equals() and hashCode based on order ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
