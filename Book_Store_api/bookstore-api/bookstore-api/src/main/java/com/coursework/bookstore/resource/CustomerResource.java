/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.resource;

import com.coursework.bookstore.model.Customer;
import com.coursework.bookstore.service.CustomerService;
import com.coursework.bookstore.api.util.DataInitializer;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private final CustomerService customerService;

    public CustomerResource() {
        DataInitializer.init();
        this.customerService = new CustomerService(DataInitializer.getCustomers());
    }

    @GET
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GET
    @Path("/{id}")
    public Customer getCustomerById(@PathParam("id") int id) {
        return customerService.getCustomerById(id);
    }

    @POST
    public Response addCustomer(Customer customer) {
        customerService.addCustomer(customer);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Customer updateCustomer(@PathParam("id") int id, Customer updatedCustomer) {
        return customerService.updateCustomer(id, updatedCustomer);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        customerService.deleteCustomer(id);
        return Response.noContent().build();
    }
}
