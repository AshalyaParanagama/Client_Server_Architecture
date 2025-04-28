/*
 * Click nbfs://nbhost/SystemFileSystem/Te
mplates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.api;

import com.coursework.bookstore.api.util.DataInitializer;
import com.coursework.bookstore.resource.AuthorResource;
import com.coursework.bookstore.resource.BookResource;
import com.coursework.bookstore.resource.CartResource;
import com.coursework.bookstore.resource.CustomerResource;
import com.coursework.bookstore.resource.OrderResource;
import com.coursework.bookstore.exception.GlobalExceptionMapper;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    // Base URI for the application
    public static final String BASE_URI = "http://localhost:8080/api/";

    // Start the server with the specified URI and resource classes
    public static HttpServer startServer() {
        // Initialize data first
        DataInitializer.init();

        // Create a ResourceConfig instance and add resource classes
        final ResourceConfig rc = new ResourceConfig()
            .packages("com.coursework.bookstore.resource") // Register the resource classes
            .register(AuthorResource.class)
            .register(BookResource.class)
            .register(CustomerResource.class)
            .register(CartResource.class)
            .register(OrderResource.class)
            .register(GlobalExceptionMapper.class); // Register exception mapper

        // Create and start the HTTP server
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        // Start the server
        final HttpServer server = startServer();
        System.out.println("Jersey app running at " + BASE_URI);
        System.out.println("Press Enter to stop...");
        System.in.read();
        server.shutdownNow(); // Stop the server when Enter is pressed
    }
}
