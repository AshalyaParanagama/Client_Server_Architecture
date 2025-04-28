/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.resource;

import com.coursework.bookstore.exception.OrderNotFoundException;
import com.coursework.bookstore.model.Order;
import com.coursework.bookstore.service.OrderService;
import com.coursework.bookstore.service.BookService;
import com.coursework.bookstore.api.util.DataInitializer;
import com.coursework.bookstore.model.Book;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    private final OrderService orderService = new OrderService();
    private final BookService bookService = new BookService();

    // Get all orders
    @GET
    public Response getAllOrders() {
        List<Order> orders = orderService.getAllOrders();  // Use the service method to fetch all orders
        return Response.ok(orders).build();
    }

    // Get an order by ID
    @GET
    @Path("/{id}")
    public Response getOrderById(@PathParam("id") int id) {
        try {
            Order order = orderService.getOrderById(id);
            return Response.ok(order).build();
        } catch (OrderNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())  // Just the message
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    // Create a new order
    @POST
    @Path("/add")
    public Response createOrder(Order order) {
        try {
            if (order == null || order.getItems() == null || order.getItems().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Order items cannot be empty.")
                        .build();
            }

            System.out.println("Received order: " + order);
            System.out.println("Items: " + order.getItems());

            // Debug: Check books availability
            List<Book> allBooks = bookService.getAllBooks();
            System.out.println("Available books count: " + (allBooks != null ? allBooks.size() : "NULL"));

            // Calculate total
            double total = order.calculateTotal(allBooks);
            System.out.println("Calculated total: " + total);
            order.setTotal(total);

            orderService.addOrder(order);

            return Response.status(Response.Status.CREATED)
                    .entity(order)
                    .build();
        } catch (Exception e) {
            e.printStackTrace(); // Log to server console
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error processing order: " + e.getMessage())
                    .build();
        }
    }

    // Update an existing order
    @PUT
    @Path("/{id}/update")
    public Response updateOrder(@PathParam("id") int id, Order updatedOrder) {
        try {
            // Verify ID match between path and body
            if (updatedOrder.getId() != id) {
                return Response.status(Response.Status.BAD_REQUEST)
                       .entity("Path ID and order ID don't match")
                       .type(MediaType.TEXT_PLAIN)
                       .build();
            }

            Order existingOrder = orderService.getOrderById(id);
            existingOrder.setItems(updatedOrder.getItems());
            existingOrder.setTotal(existingOrder.calculateTotal(bookService.getAllBooks()));
            orderService.updateOrder(existingOrder);

            return Response.ok(existingOrder).build();
        } catch (OrderNotFoundException e) {
            // Extract just the message and return as plain text
            return Response.status(Response.Status.NOT_FOUND)
                   .entity(e.getMessage())  // Just the message text
                   .type(MediaType.TEXT_PLAIN)  // Force plain text
                   .build();
        }
    }
    // Delete an order by ID
    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") int id) {
        orderService.deleteOrder(id);  // Use the service method to delete the order
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
