package com.store.project.service;

import com.store.project.model.Card;
import java.util.List;
import java.util.Optional;

public interface CardService {


    Optional<Card> getCardById(Integer id);

    List<Card> getAllCards();

    Card updateCard(Card card);

    Card rechargeCardByUserId(Integer userId, Double amount) throws Exception;

}
