/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.service;

import com.coursework.bookstore.model.Order;
import com.coursework.bookstore.exception.OrderNotFoundException;
import com.coursework.bookstore.api.util.DataInitializer;

import java.util.*;
import java.util.stream.Collectors;

public class OrderService {
    // Use DataInitializer to get the orders
    private List<Order> orders = DataInitializer.getOrders(); // Use the static list from DataInitializer

    // Add an order for a specific customer
    public void addOrder(Order newOrder) {
        orders.add(newOrder); // Add the new order to the list
    }

    // Update an order (finds it by ID and replaces it)
    public void updateOrder(Order updatedOrder) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId() == updatedOrder.getId()) {
                orders.set(i, updatedOrder);
                return;
            }
        }
        throw new OrderNotFoundException("Order with ID " + updatedOrder.getId() + " does not exist.");
    }

    // Get an order by ID (throws exception if not found)
    public Order getOrderById(int orderId) {
    for (Order order : orders) {
        if (order.getId() == orderId) {
            return order;
        }
    }
     // Throwing the exception when not found
    throw new OrderNotFoundException("Order with ID "+ " " + orderId +" "+ " does not exist.");
}

    // Get all orders across all customers
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders); // Return a copy of the list to prevent external modification
    }

    // Remove an order by ID for a specific customer
    public void removeFromOrder(int customerId, int orderId) {
        orders.removeIf(order -> order.getCustomerId() == customerId && order.getId() == orderId);
    }

    // Delete an order by ID
    public void deleteOrder(int orderId) {
        boolean isRemoved = orders.removeIf(order -> order.getId() == orderId);
        if (!isRemoved) {
            throw new OrderNotFoundException("Order with ID " + orderId + " does not exist.");
        }
    }
}
