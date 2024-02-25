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

    public Card(Integer cardNumber, User user) {
        this.cardNumber = cardNumber;
        this.user = user;
    }

    public Card() {
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
