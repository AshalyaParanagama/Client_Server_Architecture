/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.resource;

import com.coursework.bookstore.model.Author;
import com.coursework.bookstore.service.AuthorService;
import com.coursework.bookstore.api.util.DataInitializer;


import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;


@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    
    //DECLARE AND INITIALIZE authors LIST
    public static List<Author> authors = DataInitializer.getAuthors();

    //PUBLIC GETTER METHOD
    public static List<Author> getAuthorList() {
        return authors;
    }

    private final AuthorService authorService = new AuthorService();

    @POST
    public Response addAuthor(Author author, @Context UriInfo uriInfo) {
        if (author == null || author.getName() == null || author.getBiography() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid author data").build();
        }
        authorService.addAuthor(author);
        URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(author.getId())).build();
        return Response.status(Response.Status.CREATED).entity(author).build();
    }

    @GET
    public Response getAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        if (authors.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No authors found").build();
        }
        return Response.ok(authors).build();
    }

    @GET
    @Path("/{id}")
    public Response getAuthorById(@PathParam("id") int id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Author not found").build();
        }
        return Response.ok(author).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") int id, Author author) {
        Author existingAuthor = authorService.getAuthorById(id);
        if (existingAuthor == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Author not found").build();
        }

        existingAuthor.setName(author.getName());
        existingAuthor.setBiography(author.getBiography());
        existingAuthor.setNationality(author.getNationality());

        authorService.updateAuthor(existingAuthor);
        return Response.ok(existingAuthor).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Author not found").build();
        }

        authorService.deleteAuthor(id);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/all")
    public Response deleteAllAuthors() {
        authorService.deleteAllAuthors();
        return Response.noContent().build();
    }
}
