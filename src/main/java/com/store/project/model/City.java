package com.store.project.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String city;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> users;

    // Getters and setters omitted for brevity
}
