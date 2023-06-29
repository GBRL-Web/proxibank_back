package com.projet.proxibanque_groupe3.service;

import com.projet.proxibanque_groupe3.exceptions.NotFoundException;
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

    private Logger logger = LoggerFactory.getLogger(BankAccountService.class);

    public Optional<Set<BankAccount>> getBankAccountsByIdClientFromDatabase(Integer id){
        try {
            Optional<Set<BankAccount>> bankAccounts = bankAccountRepository.getByClientId((long)id);
            logger.info("Client - ID [{}] Successful retrieval of accounts from the database.", id);
            return bankAccounts;
        } catch (Exception e){
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    public Optional<BankAccount> getBankAccountByAccountNumber(Integer accountnumber){
        try {
            Optional<BankAccount> bankAccount = bankAccountRepository.getByAccountNumber(accountnumber);
            logger.info("Account [{}] Successfully retrieved from the database.", accountnumber);
            return bankAccount;
        } catch (Exception e){
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    public void transferTo(Transfer transfer) throws NotFoundException {
        // Check if the accounts are different
        if (transfer.getToAccount().equals(transfer.getFromAccount())){
            logger.warn("Transfer impossible. The transfer account numbers used are identical.");
            throw new NotFoundException("Transfer impossible. The transfer account numbers used are identical.");
        }

        // Check debited account solvability
        Optional<BankAccount> optionalAccountDebited = bankAccountRepository.getByAccountNumber(transfer.getFromAccount());
        if (optionalAccountDebited.isEmpty()) {
            logger.warn("Transfer impossible. Debited account not found.");
            throw new NotFoundException("Transfer impossible. Debited account not found.");
        }
        BankAccount accountDebited = optionalAccountDebited.get();
        if(accountDebited instanceof CheckingAccount c && (c.getBalance() + c.getOverdraft() < transfer.getAmount())){
            logger.warn("Transfer impossible. Not enough funds to transfer.");
            throw new NotFoundException("Transfer impossible. Not enough funds to transfer.");
        }

        // Transfer
        Optional<BankAccount> optionalAccountCredited = bankAccountRepository.getByAccountNumber(transfer.getToAccount());
        if (optionalAccountCredited.isEmpty()) {
            logger.warn("Transfer impossible. Credited account not found.");
            throw new NotFoundException("Transfer impossible. Credited account not found.");
        }
        BankAccount accountCredited = optionalAccountCredited.get();
        accountDebited.setBalance(accountDebited.getBalance() - transfer.getAmount());
        accountCredited.setBalance(accountCredited.getBalance() + transfer.getAmount());

        // Call the TransactionService to create the transactions
        transactionService.createTransaction(accountDebited.getAccountNumber(), "Transfer towards account nr " + accountCredited.getAccountNumber(), transfer.getAmount());
        transactionService.createTransaction(accountCredited.getAccountNumber(), "Transfer from account nr " + accountDebited.getAccountNumber(), transfer.getAmount());
    }
}
