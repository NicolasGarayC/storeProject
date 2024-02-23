package com.store.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Associed_id", nullable = false)
    private User user;

    // Getters and setters omitted for brevity
}
