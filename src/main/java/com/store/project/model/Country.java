package com.store.project.model;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String country;

    @OneToOne(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User users;

    public Country(Integer id, String country, User users) {
        this.id = id;
        this.country = country;
        this.users = users;
    }

    public Country() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
