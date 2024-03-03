package com.store.project.controller;

import com.store.project.model.dto.CardDTO;
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

    @Autowired
    private CardService cardService;

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


    @PostMapping("/recharge")
    public ResponseEntity<String> rechargeCardByUserId( @RequestBody CardDTO card) {
        try {
            Double amount = card.getAmount();
            Integer userId = card.getUserId();
            String status = cardService.rechargeCardByUserId(userId, amount);
            return ResponseEntity.ok().body(status);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
