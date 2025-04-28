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

    private static final CustomerService customerService =new CustomerService(DataInitializer.getCustomers());

    @GET
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GET
    @Path("/{id}")
    public Response getCustomerById(@PathParam("id") int id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Customer with ID " + id + " not found.").build();
        }
        return Response.ok(customer).build();
    }

    @POST
    @Path("/{id}/add")
    public Response addCustomer(Customer customer) {
        customerService.addCustomer(customer);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}/update")
    public Response updateCustomer(@PathParam("id") int id, Customer updatedCustomer) {
        Customer existingCustomer = customerService.getCustomerById(id);

        if (existingCustomer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Customer with ID " + id + " not found.").build();
        }

        Customer updated = customerService.updateCustomer(id, updatedCustomer);
        return Response.ok(updated).build();
    }


    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        customerService.deleteCustomer(id);
        return Response.noContent().build();
    }
}
