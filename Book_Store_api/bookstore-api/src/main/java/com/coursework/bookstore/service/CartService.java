/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.service;

import com.coursework.bookstore.model.CartItem;

import java.util.*;

public class CartService {
    private final Map<Integer, List<CartItem>> customerCarts = new HashMap<>();

    public List<CartItem> getCart(int customerId) {
        return customerCarts.getOrDefault(customerId, new ArrayList<>());
    }

    public void addToCart(int customerId, CartItem newItem) {
        customerCarts.computeIfAbsent(customerId, k -> new ArrayList<>());
        List<CartItem> cart = customerCarts.get(customerId);

        for (CartItem item : cart) {
            if (item.getBookId() == newItem.getBookId()) {
                item.setQuantity(item.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        cart.add(newItem);
    }

    public void removeFromCart(int customerId, int bookId) {
        List<CartItem> cart = customerCarts.get(customerId);
        if (cart != null) {
            cart.removeIf(item -> item.getBookId() == bookId);
        }
    }

    public void clearCart(int customerId) {
        customerCarts.remove(customerId);
    }

    public void updateCartItem(int customerId, CartItem updatedItem) {
        List<CartItem> cart = customerCarts.get(customerId);
        if (cart != null) {
            for (CartItem item : cart) {
                if (item.getBookId() == updatedItem.getBookId()) {
                    item.setQuantity(updatedItem.getQuantity());
                    return;
                }
            }
        }
    }

    public static Map<Integer, List<CartItem>> getCustomerCarts(Map<Integer, List<CartItem>> mapRef) {
        return mapRef;
    }

    public Map<Integer, List<CartItem>> getAllCustomerCarts() {
        return customerCarts;
    }

    public Map<Integer, List<CartItem>> getCustomerCarts() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
