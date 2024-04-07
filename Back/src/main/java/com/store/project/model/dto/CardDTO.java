package com.store.project.model.dto;

public class CardDTO {
    Integer userId;
    Double amount;

    public CardDTO(Integer userId, Double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public CardDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
