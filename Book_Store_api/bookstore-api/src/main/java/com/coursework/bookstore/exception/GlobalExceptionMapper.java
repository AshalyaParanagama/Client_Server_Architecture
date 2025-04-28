/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.exception;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException exception) {
        String errorType;
        Response.Status status;

        if (exception instanceof BookNotFoundException ||
            exception instanceof AuthorNotFoundException ||
            exception instanceof CustomerNotFoundException ||
            exception instanceof CartNotFoundException ||
            exception instanceof OrderNotFoundException) {

            errorType = getSimpleErrorName(exception);
            status = Response.Status.NOT_FOUND;

        } else if (exception instanceof InvalidInputException ||
                   exception instanceof OutOfStockException) {

            errorType = getSimpleErrorName(exception);
            status = Response.Status.BAD_REQUEST;

        } else {
            errorType = "InternalServerError";
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }

        ErrorMessage error = new ErrorMessage(errorType, exception.getMessage(), status.getStatusCode());

        return Response.status(status)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private String getSimpleErrorName(RuntimeException exception) {
        return exception.getClass().getSimpleName().replace("Exception", "").replace("NotFound", "Not Found");
    }

    public static class ErrorMessage {
        private String error;
        private String message;
        private int status;

        public ErrorMessage(String error, String message, int status) {
            this.error = error;
            this.message = message;
            this.status = status;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
