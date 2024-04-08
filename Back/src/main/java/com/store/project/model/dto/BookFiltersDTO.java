package com.store.project.model.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class BookFiltersDTO {
    private Integer id;
    private String isbn;
    private String title;
    private Float minPrice;
    private Float maxPrice;
    private String author;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publicationDateStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publicationDateEnd;    private String category;

    public BookFiltersDTO(Integer id, String isbn, String title, Float minPrice, Float maxPrice, String author, Date publicationDateStart, Date publicationDateEnd, String category) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.author = author;
        this.publicationDateStart = publicationDateStart;
        this.publicationDateEnd = publicationDateEnd;
        this.category = category;
    }

    public BookFiltersDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublicationDateStart() {
        return publicationDateStart;
    }

    public void setPublicationDateStart(Date publicationDateStart) {
        this.publicationDateStart = publicationDateStart;
    }

    public Date getPublicationDateEnd() {
        return publicationDateEnd;
    }

    public void setPublicationDateEnd(Date publicationDateEnd) {
        this.publicationDateEnd = publicationDateEnd;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
