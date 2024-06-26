package com.store.project.controller;

import com.store.project.model.dto.PurchaseDTO;
import com.store.project.service.BookService;
import com.store.project.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Map;


@RestController
@CrossOrigin
@RequestMapping("/shop")
public class ShoppingController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/purchasebooks")
    public ResponseEntity<?> purchaseBooks(@RequestBody PurchaseDTO purchase) {
        try {
            String result = purchaseService.buyBooks(purchase);
            return ResponseEntity.ok().body(Map.of("message", result));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error: " + e.getMessage()));
        }
    }
}