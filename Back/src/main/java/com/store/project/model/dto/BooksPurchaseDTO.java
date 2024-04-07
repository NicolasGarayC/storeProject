package com.store.project.model.dto;

public class BooksPurchaseDTO {
    Integer book;
    Integer units;

    public BooksPurchaseDTO(Integer book, Integer units) {
        this.book = book;
        this.units = units;
    }

    public BooksPurchaseDTO() {
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }
}
