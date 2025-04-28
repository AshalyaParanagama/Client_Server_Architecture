package com.coursework.bookstore.api.util;

import com.coursework.bookstore.model.*;
import com.coursework.bookstore.service.CartService;
import com.coursework.bookstore.service.OrderService;

import java.util.*;

public class DataInitializer {
    // Static lists to hold the data
    private static final List<Author> authors = new ArrayList<>();
    private static final List<Book> books = new ArrayList<>();
    private static final List<Customer> customers = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();

    // Services for managing orders and carts
    private static final OrderService orderService = new OrderService();
    private static final CartService cartService = new CartService();

    // Initialize data
    public static void init() {
        // Initialize authors
        Author a1 = new Author(1, "James Clear", "Author, speaker, and expert on habits, decision-making, and continuous improvement.", "American");
        Author a2 = new Author(2, "Brianna Wiest", "Writer, speaker, and the author of self-development books, known for her work on emotional intelligence.", "American");
        Author a3 = new Author(3, "Dustin Thao", "American author best known for writing contemporary romance and young adult fiction.", "American");
        Author a4 = new Author(4, "Alex Michaelides", "Greek-Cypriot author, screenwriter, and novelist known for psychological thrillers.", "Greek-Cypriot");
        Author a5 = new Author(5, "Karen M. McManus", "American author of young adult mystery novels, particularly thrillers and psychological suspense.", "American");

        Collections.addAll(authors, a1, a2, a3, a4, a5);

        // Initialize books
        Book b1 = new Book(1, "Atomic Habits", "9780735211292", 2018, 20.99, 50, a1.getId());
        Book b2 = new Book(2, "101 Essays That Will Change The Way You Think", "9781925992764", 2016, 15.99, 30, a2.getId());
        Book b3 = new Book(3, "You’ve Reached Sam", "9781250780244", 2021, 17.99, 25, a3.getId());
        Book b4 = new Book(4, "The Silent Patient", "9781250301697", 2019, 19.99, 40, a4.getId());
        Book b5 = new Book(5, "One of Us Is Next", "9780525707972", 2020, 16.99, 60, a5.getId());

        Collections.addAll(books, b1, b2, b3, b4, b5);

        // Initialize customers
        Customer c1 = new Customer(1, "Aisha Malik", "aisha@example.com");
        Customer c2 = new Customer(2, "Kavitha Rajendran", "kavitha@example.com");
        Customer c3 = new Customer(3, "Sajith Perera", "sajith@example.com");
        Customer c4 = new Customer(4, "Ann Anderson", "ann.anderson@example.com");
        Customer c5 = new Customer(5, "Kevin Meadows", "kevin.meadows@example.com");

        Collections.addAll(customers, c1, c2, c3, c4, c5);

        // Initialize orders
        Order order1 = new Order(1, 1, Arrays.asList(new CartItem(b3.getId(), 2)));
        Order order2 = new Order(2, 2, Arrays.asList(new CartItem(b1.getId(), 1), new CartItem(b5.getId(), 3)));
        Order order3 = new Order(3, 3, Arrays.asList(new CartItem(b4.getId(), 1)));
        Order order4 = new Order(4, 4, Arrays.asList(new CartItem(b2.getId(), 2), new CartItem(b4.getId(), 1)));
        Order order5 = new Order(5, 5, Arrays.asList(new CartItem(b1.getId(), 1), new CartItem(b3.getId(), 1)));

        Collections.addAll(orders, order1, order2, order3, order4, order5);

        // Add orders to OrderService
        orderService.addOrder(order1);
        orderService.addOrder(order2);
        orderService.addOrder(order3);
        orderService.addOrder(order4);
        orderService.addOrder(order5);

        // Initialize carts for each customer
        initializeCart();
    }

    // Add books to each customer's cart
    public static void initializeCart() {
        // Initialize carts for customers
        cartService.addToCart(1, new CartItem(1, 2)); // Aisha - Atomic Habits
        cartService.addToCart(1, new CartItem(2, 1)); // Aisha - 101 Essays

        cartService.addToCart(2, new CartItem(3, 1)); // Kavitha - You’ve Reached Sam
        cartService.addToCart(2, new CartItem(5, 2)); // Kavitha - One of Us Is Next

        cartService.addToCart(3, new CartItem(4, 3)); // Sajith - The Silent Patient

        cartService.addToCart(4, new CartItem(2, 1)); // Ann - 101 Essays
        cartService.addToCart(4, new CartItem(3, 2)); // Ann - You’ve Reached Sam

        cartService.addToCart(5, new CartItem(1, 1)); // Kevin - Atomic Habits
        cartService.addToCart(5, new CartItem(5, 1)); // Kevin - One of Us Is Next
    }

    // Getter methods for authors, books, customers, and orders
    public static List<Author> getAuthors() {
        return authors;
    }

    public static List<Book> getBooks() {
        return books;
    }

    public static List<Customer> getCustomers() {
        return customers;
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static OrderService getOrderService() {
        return orderService;
    }

    public static CartService getCartService() {
        return cartService;
    }
}
