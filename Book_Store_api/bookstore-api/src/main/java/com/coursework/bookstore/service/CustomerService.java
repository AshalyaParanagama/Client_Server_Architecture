/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.service;

import com.coursework.bookstore.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerService {
    private List<Customer> customers;

    public CustomerService(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getAllCustomers() {
        return customers;
    }

    public Customer getCustomerById(int id) {
        return customers.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public Customer updateCustomer(int id, Customer updatedCustomer) {
        Customer existing = getCustomerById(id);
        if (existing != null) {
            existing.setName(updatedCustomer.getName());
            existing.setEmail(updatedCustomer.getEmail());
        }
        return existing;
    }

    public void deleteCustomer(int id) {
        customers.removeIf(c -> c.getId() == id);
    }
}
