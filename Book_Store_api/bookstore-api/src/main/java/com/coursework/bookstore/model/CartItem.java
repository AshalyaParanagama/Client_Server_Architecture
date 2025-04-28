/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.model;

public class CartItem {
    private int bookId;
    private int quantity;
    
    public CartItem (){
        //Required for JSON deserialization
    }
    
    public CartItem (int bookId, int quantity){
        this.bookId = bookId;
        this.quantity = quantity;
    }
    
    public int getBookId () {
        return bookId;
    }
    
    public void setBookId (int bookId){
        this.bookId = bookId;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void setQuantity (int quantity){
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "CartItem{" +
                "bookId=" + bookId +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;
        return bookId == cartItem.bookId && quantity == cartItem.quantity;
    }

    @Override
    public int hashCode() {
        return 31 * bookId + quantity;
    }
}

