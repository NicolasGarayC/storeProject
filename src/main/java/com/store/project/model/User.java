package com.store.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @Column(nullable = false, length = 50)
    @NotNull(message = "ID can't be null.")
    @Min(value = 10000, message = "Please use a valid ID.")
    private Integer id;

    @Column(nullable = false, length = 50)
    @NotNull(message = "Your name is required.")
    @NotBlank(message = "Your name is required.")
    private String name;

    @Column(nullable = false, length = 20)
    @NotNull(message = "Your password is required.")
    @NotBlank(message = "Your password is required.")
    private String password;

    @NotNull(message = "Role is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Your country is required.")
    @JoinColumn(name = "country", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "Your city is required.")
    @JoinColumn(name = "city", nullable = false)
    private City city;

    @Column(nullable = false, length = 1)
    @NotNull(message = "Your gender is required.")
    @NotBlank(message = "Your gender is required.")
    private String gender;

    @Column(length = 50)
    @NotNull(message = "Your profession is required.")
    @NotBlank(message = "Your profession is required.")
    private String profession;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Card card;

    public User(Integer id, String name, String password, Role role, Country country, City city, String gender, String profession) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.country = country;
        this.city = city;
        this.gender = gender;
        this.profession = profession;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
