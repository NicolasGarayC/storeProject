package com.store.project.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.store.project.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByUserId(Integer userId);
}
