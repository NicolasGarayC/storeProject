package com.store.project.service.imp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.project.model.Card;
import com.store.project.repository.CardRepository;
import com.store.project.service.CardService;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Optional<Card> getCardById(Integer id) {
        return cardRepository.findById(id);
    }

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }


    @Override
    public Card rechargeCardByUserId(Integer userId, Double amount) throws Exception {
        Card card = cardRepository.findByUserId(userId);
        if (card == null) {
            throw new Exception("Card not found for user ID: " + userId);
        }

        // Validar el monto de recarga
        if (amount < 50000 || amount > 200000) {
            throw new IllegalArgumentException("Amount must be between 50,000 and 200,000.");
        }

        // Actualizar el balance de la tarjeta
        card.setBalance(card.getBalance() + amount);
        return cardRepository.save(card);
    }
}
