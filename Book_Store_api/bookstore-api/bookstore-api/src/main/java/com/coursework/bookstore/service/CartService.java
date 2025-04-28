/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.service;

import com.coursework.bookstore.model.*;
import java.util.*;

public class CartService {
    private Map<Integer, List<CartItem>> customerCarts = new HashMap<>();  // A map to store cart items for each customer

    // Get the cart (items) for a specific customer
    public List<CartItem> getCart(int customerId) {
        return customerCarts.getOrDefault(customerId, new ArrayList<>());
    }

    // Add an item to the cart for a specific customer
    public void addToCart(int customerId, CartItem item) {
        customerCarts.computeIfAbsent(customerId, k -> new ArrayList<>());
        customerCarts.get(customerId).add(item);
    }

    // Remove an item from the cart for a specific customer
    public void removeFromCart(int customerId, int bookId) {
        List<CartItem> cart = customerCarts.get(customerId);
        if (cart != null) {
            cart.removeIf(item -> item.getBookId() == bookId);
        }
    }

    // Clear the cart for a specific customer
    public void clearCart(int customerId) {
        customerCarts.remove(customerId);
    }
}
