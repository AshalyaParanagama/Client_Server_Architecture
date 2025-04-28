/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.service;

import com.coursework.bookstore.api.util.DataInitializer;
import com.coursework.bookstore.model.Author;

import java.util.List;

public class AuthorService {

    private static final List<Author> authors = DataInitializer.getAuthors();

    public void addAuthor(Author author) {
         int newId = authors.isEmpty() ? 1 : authors.get(authors.size() - 1).getId() + 1;
        author.setId(newId);
        authors.add(author);
    }

    public Author getAuthorById(int id) {
        return authors.stream()
            .filter(a -> a.getId() == id)
            .findFirst()
            .orElse(null);
    }

    public List<Author> getAllAuthors() {
        return authors;
    }

    public void updateAuthor(Author updatedAuthor) {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getId() == updatedAuthor.getId()) {
                authors.set(i, updatedAuthor);
                break;
            }
        }
    }

    public void deleteAuthor(int id) {
        authors.removeIf(a -> a.getId() == id);
    }

    public void deleteAllAuthors() {
        authors.clear();
    }
}
