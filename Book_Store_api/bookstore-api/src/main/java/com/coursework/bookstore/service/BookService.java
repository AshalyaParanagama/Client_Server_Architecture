/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.service;

import com.coursework.bookstore.api.util.DataInitializer;
import com.coursework.bookstore.model.Book;
import com.coursework.bookstore.model.Author;
import com.coursework.bookstore.resource.AuthorResource;

import java.util.List;
import java.util.Optional;

public class BookService {

    // Get all books
    public List<Book> getAllBooks() {
        return DataInitializer.getBooks(); // Fetch books from DataInitializer
    }

    // Get a book by ID
    public Book getBookById(int id) {
        // Fetch books from DataInitializer and find by ID
        return DataInitializer.getBooks().stream()
                .filter(book -> book.getId() == id)
                .findFirst()
                .orElse(null); // Return null if book not found
    }

    // Add a new book
    public Book addBook(Book book) {
        if (book == null || book.getTitle() == null || book.getIsbn() == null) {
            throw new IllegalArgumentException("Book title and ISBN must not be null");
        }
        // Get next available ID (this is managed by DataInitializer)
        int nextId = DataInitializer.getBooks().size() + 1;
        book.setId(nextId);
        
        // Match the author from authorId and set the author object
        for (Author author : AuthorResource.getAuthorList()){
            if (author.getId() == book.getAuthorId()) {
                break;
            }
        }
        DataInitializer.getBooks().add(book);
        return book;
    }

    // Update an existing book
    public Book updateBook(Book updatedBook) {
        List<Book> books = DataInitializer.getBooks();
        Optional<Book> existingBookOpt = books.stream()
                .filter(book -> book.getId() == updatedBook.getId())
                .findFirst();

        if (existingBookOpt.isPresent()) {
            Book existingBook = existingBookOpt.get();
            books.remove(existingBook);
            books.add(updatedBook);
            return updatedBook;
        }
        return null; // Book not found
    }

    // Delete a book by ID
    public boolean deleteBook(int id) {
        Book book = getBookById(id);
        if (book != null) {
            DataInitializer.getBooks().remove(book);
            return true; // Book removed successfully
        }
        return false; // Book not found
    }

    // Helper method to reset the list and ID (for testing purposes)
    public void resetBooks(List<Book> newBooks) {
        DataInitializer.getBooks().clear();
        DataInitializer.getBooks().addAll(newBooks);
    }
}
