package com.store.project.service;
import com.store.project.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book saveBook(Book book);

    Optional<Book> getBookById(Integer id);

    List<Book> getAllBooks();

    Book updateBook(Book book);

    void deleteBookById(Integer id);
}
