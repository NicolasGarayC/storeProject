package com.store.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.store.project.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // Métodos CRUD heredados
}
