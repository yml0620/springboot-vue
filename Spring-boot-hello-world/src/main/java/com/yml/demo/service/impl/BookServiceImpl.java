package com.yml.demo.service.impl;

import com.yml.demo.mapper.BookMapper;
import com.yml.demo.model.Book;
import com.yml.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    @Override
    public void delBookById(int id) {
        bookMapper.delBookById(id);
    }

    @Override
    public void updateBookById(Book book) {
        bookMapper.updateBookById(book.getName(),book.getPrice(),book.getId());
    }

    @Override
    public List<Book> getBook() {
        return bookMapper.getBook();
    }
}
