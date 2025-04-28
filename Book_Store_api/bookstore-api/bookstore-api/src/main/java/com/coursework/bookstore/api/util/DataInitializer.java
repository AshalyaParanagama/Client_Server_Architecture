/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.api.util;

import com.coursework.bookstore.model.*;
import com.coursework.bookstore.service.CartService;
import java.util.*;

public class DataInitializer {
    private static List<Author> authors = new ArrayList<>();
    private static List<Book> books = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static Random random = new Random();

    public static void init() {
        // Authors
        Author a1 = new Author(1, "James Clear", "Author, speaker, and expert on habits, decision-making, and continuous improvement.", "American");
        Author a2 = new Author(2, "Brianna Wiest", "Writer, speaker, and the author of self-development books, known for her work on emotional intelligence.", "American");
        Author a3 = new Author(3, "Dustin Thao", "American author best known for writing contemporary romance and young adult fiction.", "American");
        Author a4 = new Author(4, "Alex Michaelides", "Greek-Cypriot author, screenwriter, and novelist known for psychological thrillers.", "Greek-Cypriot");
        Author a5 = new Author(5, "Karen M. McManus", "American author of young adult mystery novels, particularly thrillers and psychological suspense.", "American");

        authors.add(a1);
        authors.add(a2);
        authors.add(a3);
        authors.add(a4);
        authors.add(a5);

        // Books
        Book b1 = new Book(1, "Atomic Habits", "A book on how small changes compound over time to create remarkable results.", "James Clear");
        Book b2 = new Book(2, "101 Essays That Will Change The Way You Think", "A collection of essays on self-improvement, perspective shifts, and personal growth.", "Brianna Wiest");
        Book b3 = new Book(3, "You’ve Reached Sam", "A heart-wrenching novel about grief and love after a young woman loses her boyfriend and receives a final text from him.", "Dustin Thao");
        Book b4 = new Book(4, "The Silent Patient", "A psychological thriller about a woman who shoots her husband and then refuses to speak, unraveling the mystery of why.", "Alex Michaelides");
        Book b5 = new Book(5, "One of Us Is Next", "A gripping young adult thriller that continues the story from *One of Us Is Lying*, with more secrets and betrayals.", "Karen M. McManus");

        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);
        books.add(b5);

        // Customers
        Customer c1 = new Customer(1, "Aisha Malik", "aisha@example.com");
        Customer c2 = new Customer(2, "Kavitha Rajendran", "kavitha@example.com");
        Customer c3 = new Customer(3, "Sajith Perera", "sajith@example.com");

        customers.add(c1);
        customers.add(c2);
        customers.add(c3);

        // Adding random cart items for each customer
        for (Customer customer : customers) {
            List<CartItem> cartItems = new ArrayList<>();
            int numItems = random.nextInt(3) + 2;  // Random number of items (2-4)

            for (int i = 0; i < numItems; i++) {
                Book randomBook = books.get(random.nextInt(books.size()));
                int quantity = random.nextInt(3) + 1;  // Random quantity (1-3)
                cartItems.add(new CartItem(randomBook.getId(), quantity));
            }

            // Store the cart items for the customer (initialized as part of the order process)
            CartService cartService = new CartService();
            cartService.addToCart(customer.getId(), cartItems.get(0));  // Add the first item to cart as an example
        }
    }

    public static List<Author> getAuthors() {
        return authors;
    }

    public static List<Book> getBooks() {
        return books;
    }

    public static List<Customer> getCustomers() {
        return customers;
    }
}
