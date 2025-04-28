/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dinulihasarahendawitharana
 */
package com.coursework.bookstore.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.coursework.bookstore.model.Order;
import com.coursework.bookstore.model.CartItem;
import com.coursework.bookstore.service.OrderService;

import java.util.List;

@Path("/customers/{customerId}/orders")  // This path includes the customerId in the URL
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    private final OrderService orderService = new OrderService();

    // Get all orders for a specific customer
    @GET
    public List<Order> getOrdersForCustomer(@PathParam("customerId") int customerId) {
        return orderService.getOrdersForCustomer(customerId);
    }

    // Place an order (finalize the cart as an order) for a customer
    @POST
    public Response placeOrder(@PathParam("customerId") int customerId, List<CartItem> items) {
        // Create an order with provided items
        Order newOrder = new Order(0, customerId, items);  // ID will be set when adding to the service
        orderService.addToOrder(customerId, newOrder); // Add the order
        return Response.status(Response.Status.CREATED).build();
    }

    // Remove a specific order for a customer (if needed)
    @DELETE
    @Path("/{orderId}")
    public Response removeOrder(@PathParam("customerId") int customerId, @PathParam("orderId") int orderId) {
        orderService.removeFromOrder(customerId, orderId);
        return Response.noContent().build();
    }
}
