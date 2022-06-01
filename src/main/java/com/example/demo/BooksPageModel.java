package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class BooksPageModel {
    private List<Book> books;
 
    // default and parameterized constructor
 
    public void addBook(Book book) {
        this.books.add(book);
    }

    /**
     * @return the books
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    /**
     * @param books
     */
    public BooksPageModel() {
        this.books = new ArrayList<Book>();
    }
     
    // getter and setter
}