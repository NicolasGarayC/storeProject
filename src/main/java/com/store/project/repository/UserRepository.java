package com.store.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.store.project.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Métodos CRUD heredados
}
