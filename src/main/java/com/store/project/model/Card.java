package com.store.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cardNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Associed_id", nullable = false)
    private User user;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Double Balance;


    public Card(Integer cardNumber, User user) {
        this.cardNumber = cardNumber;
        this.user = user;
        this.Balance = 0.00;
    }

    public Card() {
        this.Balance = 0.00;
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

    public Double getBalance() {
        return Balance;
    }

    public void setBalance(Double balance) {
        Balance = balance;
    }
}
