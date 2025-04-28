/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.coursework.bookstore.model;

import java.util.List;
import java.util.Objects;

public class Order {
    private int id;
    private int customerId;
    private List<CartItem> items;

    // Constructor to initialize the Order with id, customerId, and a list of CartItems
    public Order(int id, int customerId, List<CartItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
    }

    // Getter for the order id
    public int getId() {
        return id;
    }

    // Setter for the order id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for the customer id
    public int getCustomerId() {
        return customerId;
    }

    // Setter for the customer id
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // Getter for the list of cart items
    public List<CartItem> getItems() {
        return items;
    }

    // Setter for the list of cart items
    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    // Override toString() for easier debugging/logging
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", items=" + items +
                '}';
    }

    // Override equals() to ensure orders with the same id and customerId are treated as equal
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Order order = (Order) obj;
        return id == order.id && customerId == order.customerId && items.equals(order.items);
    }

    // Override hashCode() to maintain contract with equals()
    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, items);
    }
}
