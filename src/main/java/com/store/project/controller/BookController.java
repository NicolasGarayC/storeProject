package com.store.project.controller;
import com.store.project.model.dto.BookFiltersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.store.project.model.Book;
import com.store.project.service.BookService;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/insertBook")
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        try{
            bookService.saveBook(book);
            return new ResponseEntity<>("Book Created",HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println("Error"+ e.getMessage());
            return new ResponseEntity<>("Internal Error.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody Book bookDetails) {
        bookDetails.setId(id);
        Book updatedBook = bookService.updateBook(bookDetails);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable Integer id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Book>> findBooks(@RequestBody BookFiltersDTO criteria, Pageable pageable) {
        Page<Book> books = bookService.findBooks(criteria.getId(), criteria.getTitle(), criteria.getIsbn(), pageable);
        return ResponseEntity.ok(books);
    }

}
