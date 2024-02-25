package com.store.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String isbn;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private Float price;

    @Column(name = "units_Available", length = 50)
    private String unitsAvailable;

    @Column(name = "image", nullable = false)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", nullable = false)
    private Category category;



    public Book() {
    }


    public Book(Integer id, String isbn, String title, Float price, String unitsAvailable, String image, Category category) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.unitsAvailable = unitsAvailable;
        this.image = image;
        this.category = category;
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

    public String getUnitsAvailable() {
        return unitsAvailable;
    }

    public void setUnitsAvailable(String unitsAvailable) {
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
}
