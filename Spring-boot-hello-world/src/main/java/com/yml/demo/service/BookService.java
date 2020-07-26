package com.yml.demo.service;

import com.yml.demo.model.Book;

import java.util.List;

public interface BookService {

    void addBook(Book book);

    void delBookById(int id);

    void updateBookById(Book book);

    List<Book> getBook();
}
