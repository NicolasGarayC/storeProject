package com.store.project.model;
import jakarta.persistence.*;


    @Entity
    @Table(name = "Users")
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "name", nullable = false)
        private String name;

        @Column(name = "password", nullable = false)
        private String password;

        @Column(name = "role", nullable = false)
        private Integer role;

        @Column(name = "country", nullable = false)
        private Integer country;

        @Column(name = "city", nullable = false)
        private Integer city;

        @Column(name = "gender", nullable = false)
        private String gender;

        @Column(name = "profession")
        private String profession;

        public User(Integer id, String name, String password, Integer role, Integer country, Integer city, String gender, String profession) {
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

        public Integer getRole() {
            return role;
        }

        public void setRole(Integer role) {
            this.role = role;
        }

        public Integer getCountry() {
            return country;
        }

        public void setCountry(Integer country) {
            this.country = country;
        }

        public Integer getCity() {
            return city;
        }

        public void setCity(Integer city) {
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
    }

