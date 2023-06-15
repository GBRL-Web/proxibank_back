package com.projet.proxibanque_groupe3.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projet.proxibanque_groupe3.model.Transfer;
import com.projet.proxibanque_groupe3.service.TransferService;

@RestController
@RequestMapping("/transfers")
public class TransferController {
    private TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<String> createTransfer(@RequestBody Transfer transfer) {
        // Perform transfer logic using transferService
        transferService.performTransfer(transfer.getFromAccount(), transfer.getToAccount(), transfer.getAmount());
        
        // Return a success response
        return ResponseEntity.status(HttpStatus.CREATED).body("Transfer created successfully.");
    }
}
