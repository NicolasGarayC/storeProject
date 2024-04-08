package com.store.project.service;
import com.store.project.model.Book;
import com.store.project.model.dto.BookFiltersDTO;
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

    Page<Book> findBooks(BookFiltersDTO criteria, Pageable pageable);

}
