package com.projet.proxibanque_groupe3.service;

import com.projet.proxibanque_groupe3.ProxibanqueGroupe3Application;
import com.projet.proxibanque_groupe3.model.BankAccount;
import com.projet.proxibanque_groupe3.model.CheckingAccount;
import com.projet.proxibanque_groupe3.model.Transfer;
import com.projet.proxibanque_groupe3.persistance.BankAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private TransactionService transactionService;

    private Logger logger = LoggerFactory.getLogger(ProxibanqueGroupe3Application.class);

    public Optional<Set<BankAccount>> getBankAccountsByIdClientFromDatabase(Integer id){
        Optional<Set<BankAccount>> bankAccounts = null;
        try {
            bankAccounts = bankAccountRepository.getBankAccountsByClient_Id((long)id);
            logger.info("Client - ID [" + id + "] Successful retrieval of accounts from database.");
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return bankAccounts;
    }

    public Optional<BankAccount> getBankAccountByAccountNumber(Integer accountnumber){

        Optional<BankAccount> bankAccount = null;
        try {
            bankAccount = bankAccountRepository.getBankAccountByAccountNumber(accountnumber);
            logger.info("Account [" + accountnumber + "] Successfully retrieved from database.");
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return bankAccount;
    }

    public void transferTo(Transfer transfer) throws Exception {

        // Check if the accounts are differents
        if (transfer.getToAccount().equals(transfer.getFromAccount())){
            logger.warn("Transfer impossible. The transfer account numbers used are identical.");
            throw new Exception("Transfer impossible. The transfer account numbers used are identical.");
        }

        // Check debited account solvability
        BankAccount accountDebited = bankAccountRepository.getBankAccountByAccountNumber(transfer.getFromAccount()).get();
        if(accountDebited instanceof CheckingAccount c && (c.getBalance() + c.getOverdraft() < transfer.getAmount())){
            logger.warn("Transfer impossible. Not enough funds to transfer.");
            throw new Exception("Transfer impossible. Not enough funds to transfer.");
        }

        // Transfert
        BankAccount accountCredited = bankAccountRepository.getBankAccountByAccountNumber(transfer.getToAccount()).get();
        accountDebited.setBalance(accountDebited.getBalance() - transfer.getAmount());
        accountCredited.setBalance(accountCredited.getBalance() + transfer.getAmount());

        // Call the TransactionService to create the trasactions
        transactionService.createTransaction(accountDebited.getAccountNumber(), "Transfer towards account nr " + accountCredited.getAccountNumber(), transfer.getAmount());
        transactionService.createTransaction(accountCredited.getAccountNumber(), "Transfer from account nr " + accountDebited.getAccountNumber(), transfer.getAmount());
    }


}
