package com.relatosdepapel.payments.controller;

import com.relatosdepapel.payments.model.Purchase;
import com.relatosdepapel.payments.service.PurchaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@Valid @RequestBody Purchase purchase) {
        return ResponseEntity.ok(purchaseService.processPurchase(purchase));
    }
} 