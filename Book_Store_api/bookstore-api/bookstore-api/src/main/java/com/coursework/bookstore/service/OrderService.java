/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.service;

import com.coursework.bookstore.model.*;
import java.util.*;

public class OrderService {
    // A map to store orders for each customer (key: customerId, value: list of orders)
    private static Map<Integer, List<Order>> customerOrders = new HashMap<>();

    // Get all orders for a specific customer
    public List<Order> getOrdersForCustomer(int customerId) {
        return customerOrders.getOrDefault(customerId, new ArrayList<>());
    }

    // Add an order for a customer
    public void addToOrder(int customerId, Order newOrder) {
        customerOrders.computeIfAbsent(customerId, k -> new ArrayList<>()).add(newOrder);
    }

    // Remove an order for a customer (if needed)
    public void removeFromOrder(int customerId, int orderId) {
        List<Order> orders = customerOrders.get(customerId);
        if (orders != null) {
            orders.removeIf(order -> order.getId() == orderId);
        }
    }

    // Place an order (finalize the cart as an order) for a customer
    public void placeOrder(int customerId) {
        List<Order> orders = customerOrders.get(customerId);
        if (orders != null && !orders.isEmpty()) {
            // Here, you might want to process the order further (e.g., save to a database).
            customerOrders.remove(customerId); // Remove orders once placed
        }
    }
}
