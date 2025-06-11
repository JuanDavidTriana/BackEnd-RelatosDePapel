package com.relatosdepapel.payments.service;

import com.relatosdepapel.payments.client.BookDTO;
import com.relatosdepapel.payments.client.BookServiceClient;
import com.relatosdepapel.payments.model.Purchase;
import com.relatosdepapel.payments.model.PurchaseStatus;
import com.relatosdepapel.payments.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private BookServiceClient bookServiceClient;

    @Transactional
    public Purchase processPurchase(Purchase purchase) {
        // Validate book exists and is available
        ResponseEntity<BookDTO> bookResponse = bookServiceClient.getBookById(purchase.getBookId());
        
        if (!bookResponse.getStatusCode().is2xxSuccessful() || bookResponse.getBody() == null) {
            throw new RuntimeException("Book not found or not available");
        }

        BookDTO book = bookResponse.getBody();
        
        if (!book.isVisibility()) {
            throw new RuntimeException("Book is not available for purchase");
        }

        // Calculate total amount
        purchase.setTotalAmount(book.getPrice() * purchase.getQuantity());
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setStatus(PurchaseStatus.CONFIRMED);

        // Save purchase
        return purchaseRepository.save(purchase);
    }
} 