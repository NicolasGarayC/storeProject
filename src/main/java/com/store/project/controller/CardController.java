package com.store.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.store.project.model.Card;
import com.store.project.service.CardService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card) {
        Card savedCard = cardService.saveCard(card);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCard(@PathVariable Integer id) {
        cardService.deleteCardById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
