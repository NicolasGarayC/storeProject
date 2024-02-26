package com.store.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.store.project.model.Card;
import com.store.project.service.CardService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable Integer id) {
        Optional<Card> card = cardService.getCardById(id);
        return card.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards() {
        List<Card> cards = cardService.getAllCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable Integer id, @RequestBody Card cardDetails) {
        cardDetails.setCardNumber(id); // Asegúrate de que esto corresponde a cómo manejas el identificador en Card.
        Card updatedCard = cardService.updateCard(cardDetails);
        return new ResponseEntity<>(updatedCard, HttpStatus.OK);
    }

    @PostMapping("/recharge/user/{userId}")
    public ResponseEntity<?> rechargeCardByUserId(@PathVariable Integer userId, @RequestBody Map<String, Double> payload) {
        try {
            Double amount = payload.get("amount");
            Card updatedCard = cardService.rechargeCardByUserId(userId, amount);
            return ResponseEntity.ok(updatedCard);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
