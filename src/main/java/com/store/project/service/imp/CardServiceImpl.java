package com.store.project.service.imp;


import com.store.project.model.User;
import com.store.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.store.project.model.Card;
import com.store.project.repository.CardRepository;
import com.store.project.service.CardService;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

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
    public String rechargeCardByUserId(Integer userId, Double amount) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new Exception("User not found for ID: " + userId);
        }
        Card card = cardRepository.findByUserId(userId);
        if (card == null) {
            throw new Exception("Card not found for user ID: " + userId);
        }

        if (amount < 50000 || amount > 200000) {
            throw new IllegalArgumentException("Amount must be between 50,000 and 200,000.");
        }

        Double tempb = card.getBalance() + amount;
        System.out.println(tempb+ " " + amount);
        card.setBalance(tempb);
        try{
            cardRepository.save(card);
        } catch(Exception e) {
            throw new RuntimeException("ERROR "+e.getMessage());
        }
        return "Card Recharged";
    }
}
