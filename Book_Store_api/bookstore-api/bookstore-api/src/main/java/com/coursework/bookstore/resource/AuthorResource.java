/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.resource;

import com.coursework.bookstore.service.AuthorService;
import com.coursework.bookstore.model.Author;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    public static void add(Author a1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private AuthorService authorService;

    // Constructor to initialize authorService
    public AuthorResource() {
        this.authorService = new AuthorService(); // Initialize here or use dependency injection
    }

    // Endpoint to add a new author
    @POST
    public Response addAuthor(Author author) {
        authorService.addAuthor(author); // Add author logic
        return Response.status(Response.Status.CREATED).build();
    }

    // Endpoint to get the list of all authors
    @GET
    public List<Author> getAuthors() {
        return authorService.getAllAuthors();
    }

    // Endpoint to get a specific author by ID
    @GET
    @Path("/{id}")
    public Response getAuthorById(@PathParam("id") int id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.OK).entity(author).build();
    }

    // Endpoint to update an existing author
    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") int id, Author author) {
        Author existingAuthor = authorService.getAuthorById(id);
        if (existingAuthor == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        existingAuthor.setName(author.getName());
        existingAuthor.setBiography(author.getBiography());
        existingAuthor.setNationality(author.getNationality());

        authorService.updateAuthor(existingAuthor);
        return Response.status(Response.Status.OK).entity(existingAuthor).build();
    }

    // Endpoint to delete an author by ID
    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        authorService.deleteAuthor(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    // Endpoint to delete all authors
    @DELETE
    @Path("/all")
    public Response deleteAllAuthors() {
        authorService.deleteAllAuthors();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
