package com.example.demo.models;

import java.util.List;

public class Order {
    private String id;
    private List<Book> books;

    public Order() {
    }

    public Order(String id, List<Book> books) {
        this.id = id;
        this.books = books;
    }

    public Order(List<Book> books) {
        this.books = books;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
