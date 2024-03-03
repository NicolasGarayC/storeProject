package com.store.project.service;
import com.store.project.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    String saveBook(Book book);

    Optional<Book> getBookById(Integer id);

    List<Book> getAllBooks();

    Book updateBook(Book book);

    void deleteBookById(Integer id);

    Page<Book> findBooks(Integer id, String title, String isbn, Pageable pageable);

}
