package com.coursework.bookstore.resource;

import com.coursework.bookstore.model.Book;
import com.coursework.bookstore.service.BookService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    private static final BookService bookService = new BookService();

    // Simple GET method to return all books
    @GET
    public Response getBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(books).build();
    }

    // POST method to create a new book
    @POST
    public Response createBook(Book book) {
        try {
            System.out.println("Received book: " + book); // Log book details for debugging
            Book createdBook = bookService.addBook(book); // Add the book to the service
            return Response.status(Response.Status.CREATED).entity(createdBook).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // GET method to retrieve a book by its ID
    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") int id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Book with ID " + id + " not found.")
                           .build();
        }
        return Response.ok(book).build();
    }

    // PUT method to update an existing book by ID
    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") int id, Book updatedBook) {
        updatedBook.setId(id); // Set the ID of the book to ensure correct update
        Book book = bookService.updateBook(updatedBook);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Book with ID " + id + " not found.")
                           .build();
        }
        return Response.ok(book).build();
    }

    // DELETE method to delete a book by its ID
    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Book with ID " + id + " not found.")
                           .build();
        }
        bookService.deleteBook(id);
        return Response.noContent().build();
    }
}
