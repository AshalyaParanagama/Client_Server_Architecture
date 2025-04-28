/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.coursework.bookstore.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import com.coursework.bookstore.model.Book;
import com.coursework.bookstore.service.BookService;
import com.coursework.bookstore.api.util.DataInitializer;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    private final BookService bookService;

    // Constructor to initialize bookService
    public BookResource() {
        DataInitializer.init();  // Initialize the data
        this.bookService = new BookService(DataInitializer.getBooks());  // Pass initialized books to service
    }

    @GET
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GET
    @Path("/{id}")
    public Book getBookById(@PathParam("id") int id) {
        return bookService.getBookById(id);
    }

    @POST
    public Response addBook(Book book) {
        bookService.addBook(book);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Book updateBook(@PathParam("id") int id, Book updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        bookService.deleteBook(id);
        return Response.noContent().build();
    }
}
