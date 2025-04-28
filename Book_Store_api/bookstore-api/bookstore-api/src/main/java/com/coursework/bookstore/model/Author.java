/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.coursework.bookstore.model;

public class Author {
    private int id;
    private String name;
    private String biography;
    private String nationality;  // Add nationality field

    public Author() {}

    public Author(int id, String name, String biography, String nationality) {
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.nationality = nationality;  // Initialize nationality
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }

    public String getNationality() { return nationality; }  // Getter for nationality
    public void setNationality(String nationality) { this.nationality = nationality; }  // Setter for nationality
}
