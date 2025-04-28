/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.coursework.bookstore.model.CartItem;
import com.coursework.bookstore.service.CartService;
import com.coursework.bookstore.api.util.DataInitializer;
import com.coursework.bookstore.model.Order;

import java.util.List;
import java.util.Map;

@Path("/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    private final CartService cartService = DataInitializer.getCartService();

    // Get all carts (for all customers)
    @GET
    @Path("/all")
    public Response getAllCarts() {
        try {
            Map<Integer, List<CartItem>> allCarts = cartService.getAllCustomerCarts();

            // Check if the cart data is available
            if (allCarts == null || allCarts.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("No carts found or no customers have carts.").build();
            }

            return Response.ok(allCarts).build();
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace to help debug
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("An error occurred while fetching carts: " + e.getMessage()).build();
        }
    }

    // Get the cart for a specific customer
    @GET 
    @Path("/customers/{customerId}")
    public Response getCustomerCart(@PathParam("customerId") int customerId) {
        List<CartItem> cart = cartService.getCart(customerId);

        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Customer with ID " + customerId + " not found or cart does not exist.").build();
        }

        return Response.ok(cart).build();
    }

    // Add an item to the cart
    @POST
    @Path("/customers/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addToCart(@PathParam("customerId") int customerId, CartItem item) {
        cartService.addToCart(customerId, item);
        return Response.status(Response.Status.CREATED)
                       .entity("Item added to cart for customer " + customerId)
                       .build();
    }

    // Update quantity of a cart item
    @PUT
    @Path("/customers/{customerId}/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCartItem(@PathParam("customerId") int customerId, CartItem item) {
        List<CartItem> cart = cartService.getCart(customerId);

        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Customer with ID " + customerId + " not found.").build();
        }

        boolean found = cart.stream().anyMatch(ci -> ci.getBookId() == item.getBookId());
        if (!found) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Item with Book ID " + item.getBookId() + " not found in cart.").build();
        }

        cartService.updateCartItem(customerId, item);
        return Response.ok("Cart item updated").build();
    }

    // Remove an item from the cart
    @DELETE
    @Path("/customers/{customerId}/remove/{bookId}")
    public Response removeFromCart(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId) {
        List<CartItem> cart = cartService.getCart(customerId);

        if (cart == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Customer not found or cart is empty.").build();
        }

        boolean found = cart.stream().anyMatch(item -> item.getBookId() == bookId);
        if (!found) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Book ID " + bookId + " not found in customer's cart.").build();
        }

        cartService.removeFromCart(customerId, bookId);
        return Response.noContent().build();
    }

    // Clear the entire cart for a customer
    @DELETE
    @Path("/customers/{customerId}/clear")
    public Response clearCart(@PathParam("customerId") int customerId) {
        cartService.clearCart(customerId);

        // After clearing the cart, explicitly ensure that the customer cart exists but is empty
        cartService.getCart(customerId);  // This ensures the cart exists but is empty
        
        return Response.noContent().build();
    }
}
