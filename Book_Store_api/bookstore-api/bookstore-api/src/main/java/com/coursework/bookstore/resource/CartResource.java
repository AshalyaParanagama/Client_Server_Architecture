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

import java.util.List;

@Path("/customers/{customerId}/cart")  // This path includes the customerId in the URL
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private final CartService cartService = new CartService();

    // Get the cart for a specific customer
    @GET
    public List<CartItem> getCart(@PathParam("customerId") int customerId) {
        return cartService.getCart(customerId);
    }

    // Add an item to the cart for a customer
    @POST
    @Path("/add")
    public Response addToCart(@PathParam("customerId") int customerId, CartItem item) {
        cartService.addToCart(customerId, item);
        return Response.status(Response.Status.CREATED).build();
    }

    // Remove an item from the cart for a customer
    @DELETE
    @Path("/remove/{bookId}")
    public Response removeFromCart(@PathParam("customerId") int customerId, @PathParam("bookId") int bookId) {
        cartService.removeFromCart(customerId, bookId);
        return Response.noContent().build();
    }

    // Clear the cart for a customer
    @DELETE
    @Path("/clear")
    public Response clearCart(@PathParam("customerId") int customerId) {
        cartService.clearCart(customerId);
        return Response.noContent().build();
    }
}
