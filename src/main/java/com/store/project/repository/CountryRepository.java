package com.store.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.store.project.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    // Métodos CRUD heredados
}
