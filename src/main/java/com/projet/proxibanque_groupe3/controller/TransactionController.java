package com.projet.proxibanque_groupe3.controller;

import com.projet.proxibanque_groupe3.model.Transaction;
import com.projet.proxibanque_groupe3.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @CrossOrigin(origins = "*")
    @GetMapping("/{flag}")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable String flag){
        List<Transaction> transactions = new ArrayList<Transaction>();
        try {
            transactions = transactionService.getTransactionFromDatabase(flag);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

        if(transactions.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(transactions);
        }
    }
}
