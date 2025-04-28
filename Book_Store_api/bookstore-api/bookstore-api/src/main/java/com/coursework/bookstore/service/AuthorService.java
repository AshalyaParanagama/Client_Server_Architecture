/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.service;

import com.coursework.bookstore.model.*;
import com.coursework.bookstore.api.util.DataInitializer;
import java.util.*;
import java.util.stream.Collectors;

public class AuthorService {
    private List<Author> authors = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    // Constructor to initialize with dummy data
    public AuthorService() {
        DataInitializer.init();  // Initialize dummy data here
        this.authors = DataInitializer.getAuthors();  // Assuming DummyDataInitializer can return authors
        this.books = DataInitializer.getBooks();  // Assuming DummyDataInitializer can return books
    }

    public List<Author> getAllAuthors() {
        return authors;
    }

    public Author getAuthorById(int id) {
        return authors.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Author addAuthor(Author author) {
        authors.add(author);
        return author;
    }

    public Author updateAuthor(int id, Author updated) {
        Author author = getAuthorById(id);
        if (author != null) {
            author.setName(updated.getName());
            author.setBiography(updated.getBiography());
            author.setNationality(updated.getNationality());
            return author;
        }
        return null;
    }

    public void deleteAuthor(int id) {
        authors.removeIf(a -> a.getId() == id);
    }

    public List<Book> getBooksByAuthor(int authorId) {
        Author author = getAuthorById(authorId);
        if (author == null) return Collections.emptyList();

        return books.stream()
                .filter(b -> b.getAuthorName().equalsIgnoreCase(author.getName()))
                .collect(Collectors.toList());
    }

    // Setter methods for injecting dummy data
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    // Implement deleteAllAuthors to remove all authors
    public void deleteAllAuthors() {
        authors.clear();  // This clears the entire list of authors
    }

    // Implement updateAuthor to update an existing author
    public void updateAuthor(Author existingAuthor) {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getId() == existingAuthor.getId()) {
                authors.set(i, existingAuthor);  // Update the author in the list
                return;
            }
        }
    }
}
