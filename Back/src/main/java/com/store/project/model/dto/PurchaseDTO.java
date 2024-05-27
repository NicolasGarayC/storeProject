package com.store.project.model.dto;

import java.util.ArrayList;

public class PurchaseDTO {

    ArrayList<BooksPurchaseDTO> books;
    Integer idUser;
    String ip;

    public PurchaseDTO() {
    }

    public PurchaseDTO(ArrayList<BooksPurchaseDTO> books, Integer idUser, String ip) {
        this.books = books;
        this.idUser = idUser;
        this.ip = ip;
    }

    public ArrayList<BooksPurchaseDTO> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<BooksPurchaseDTO> books) {
        this.books = books;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
