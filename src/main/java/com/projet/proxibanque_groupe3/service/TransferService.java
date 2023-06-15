package com.projet.proxibanque_groupe3.service;
import org.springframework.stereotype.Service;

@Service
public class TransferService {
    private TransactionService transactionService;

    public TransferService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    public void performTransfer(Integer fromAccount, Integer toAccount, Float amount) {
        // Add your transfer logic here
        System.out.println("Performing transfer from " + fromAccount + " to " + toAccount + " of amount " + amount);
        
        String string1 = "Transferred to Account " + toAccount + " , " + amount + "euros.";
        this.transactionService.createTransaction(fromAccount, string1 , amount);
        String string2 = "Transferred from Account " + fromAccount + " , " + amount + "euros.";
        this.transactionService.createTransaction(toAccount, string2 , amount);
    }
}
