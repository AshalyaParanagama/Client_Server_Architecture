/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.service;

import com.coursework.bookstore.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    private List<Book> books; // Instance variable to hold the list of books

    // Constructor to initialize BookService with the books list
    public BookService(List<Book> books) {
        this.books = books;
    }

    // Default constructor
    public BookService() {
        this.books = new ArrayList<>();
    }

    // Fetch all books
    public List<Book> getAllBooks() {
        return books;
    }

    // Fetch a book by ID
    public Book getBookById(int id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Fetch books by author name
    public List<Book> getBooksByAuthorName(String authorName) {
        return books.stream()
                .filter(b -> b.getAuthorName().equalsIgnoreCase(authorName))
                .collect(Collectors.toList());
    }

    // Delete a book by ID
    public void deleteBook(int id) {
        books.removeIf(b -> b.getId() == id);
    }

    // Update a book by ID
    public Book updateBook(int id, Book updatedBook) {
        Book book = getBookById(id);
        if (book != null) {
            book.setTitle(updatedBook.getTitle());
            book.setDescription(updatedBook.getDescription()); // Set description
            book.setAuthorName(updatedBook.getAuthorName());
        }
        return book;
    }

    // Add a new book
    public void addBook(Book book) {
        books.add(book);
    }

    // Optional: Set the list of books if needed
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
