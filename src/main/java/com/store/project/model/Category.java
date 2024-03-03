package com.store.project.model;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(nullable = false, length = 50)
    private String category;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Book books;

    public Category(Integer id, String category, Book books) {
        this.id = id;
        this.category = category;
        this.books = books;
    }

    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Book getBooks() {
        return books;
    }

    public void setBooks(Book books) {
        this.books = books;
    }
}
