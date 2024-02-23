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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category", nullable = false)
    private Category category;

    // Getters and setters omitted for brevity
}
