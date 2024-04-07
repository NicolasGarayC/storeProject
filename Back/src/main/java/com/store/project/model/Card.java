package com.store.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Cards")
public class Card {

    @Id
    @Column(name = "card_number",nullable = false, length = 50)
    private Integer cardNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "balance", nullable = false, length = 50)
    private Double balance;


    public Card(Integer cardNumber, User user) {
        this.cardNumber = cardNumber;
        this.user = user;
        this.balance = 0.00;
    }

    public Card() {
        this.balance = 0.00;
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
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
