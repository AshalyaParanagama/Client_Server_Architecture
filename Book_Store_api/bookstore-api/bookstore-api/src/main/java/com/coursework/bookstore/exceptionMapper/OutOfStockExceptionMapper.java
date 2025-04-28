/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.exceptionMapper;

/**
 *
 * @author HP
 */
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import com.coursework.bookstore.exception.*;

@Provider
public class OutOfStockExceptionMapper implements ExceptionMapper<OutOfStockException> {

    @Override
    public Response toResponse(OutOfStockException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                       .entity("Out of stock: " + exception.getMessage())
                       .build();
    }
}
