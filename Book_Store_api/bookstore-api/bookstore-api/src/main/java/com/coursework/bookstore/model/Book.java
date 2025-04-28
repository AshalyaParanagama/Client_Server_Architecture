/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.model;

public class Book {
    private int id;
    private String title;
    private String description; 
    private String authorName;

    // Default constructor
    public Book() {}

    // ✅ Full constructor with description
    public Book(int id, String title, String description, String authorName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.authorName = authorName;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }
}
