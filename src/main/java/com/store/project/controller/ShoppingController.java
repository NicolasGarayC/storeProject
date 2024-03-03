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


@RestController
@RequestMapping("/shop")
public class ShoppingController {

    @Autowired
    private PurchaseService purchaseService;
    @PostMapping("/purchasebooks")
        public ResponseEntity<String> purchaseBooks(@RequestBody PurchaseDTO purchase) {
        try{
            return new ResponseEntity<>(purchaseService.buyBooks(purchase), HttpStatus.CREATED);
        }catch (Exception e){
            System.out.println("Error"+ e.getMessage());
            return new ResponseEntity<>("Error: "+ e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
