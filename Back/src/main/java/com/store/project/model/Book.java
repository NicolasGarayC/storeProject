package com.store.project.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(nullable = false, length = 50)
    private String isbn;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private Float price;

    @Column(name = "units_Available", length = 50)
    private Integer unitsAvailable;

    @Column(name = "image", nullable = false)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "category", referencedColumnName = "id")
    private Category category;
    @Column(name = "publication_date",nullable = true)
    private Date publication_date;

    @Column(name = "author", nullable = true)
    private String author;

    public Book() {
    }

    public Book(Integer id, String isbn, String title, Float price, Integer unitsAvailable, String image, Category category, Date publication_date, String author) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.unitsAvailable = unitsAvailable;
        this.image = image;
        this.category = category;
        this.publication_date = publication_date;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getUnitsAvailable() {
        return unitsAvailable;
    }

    public void setUnitsAvailable(Integer unitsAvailable) {
        this.unitsAvailable = unitsAvailable;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Date publication_date) {
        this.publication_date = publication_date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}