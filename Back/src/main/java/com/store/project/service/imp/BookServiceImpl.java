package com.store.project.service.imp;

import com.store.project.model.dto.BookFiltersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.project.model.Book;
import com.store.project.service.BookService;
import com.store.project.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public String saveBook(Book book) {
        try{
            bookRepository.save(book);
            return "Book saved";
        }catch (Exception e){
            return "Internal error.";
        }
    }

    @Override
    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> findBooks(BookFiltersDTO criteria, Pageable pageable) {
        Specification<Book> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (criteria.getId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("id"), criteria.getId()));
            }
            if (criteria.getTitle() != null && !criteria.getTitle().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + criteria.getTitle().toLowerCase() + "%"));
            }
            if (criteria.getIsbn() != null && !criteria.getIsbn().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("isbn"), criteria.getIsbn()));
            }
            if (criteria.getMinPrice() != null && criteria.getMaxPrice() != null) {
                predicates.add(criteriaBuilder.between(root.get("price"), criteria.getMinPrice(), criteria.getMaxPrice()));
            }
            if (criteria.getAuthor() != null && !criteria.getAuthor().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("author"), criteria.getAuthor()));
            }
            if (criteria.getPublicationDateStart() != null && criteria.getPublicationDateEnd() != null) {
                predicates.add(criteriaBuilder.between(root.get("publication_date"), criteria.getPublicationDateStart(), criteria.getPublicationDateEnd()));
            }
            if (criteria.getCategory() != null && !criteria.getCategory().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("category").get("name"), criteria.getCategory()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        return bookRepository.findAll(spec, pageable);
    }


}
