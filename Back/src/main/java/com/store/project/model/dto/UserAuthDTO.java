package com.store.project.model.dto;

import java.math.BigInteger;

public class UserAuthDTO {
    private BigInteger user;
    private String pass;

    public UserAuthDTO() {
    }

    public UserAuthDTO(BigInteger user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public BigInteger getUser() {
        return user;
    }

    public void setUser(BigInteger user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
