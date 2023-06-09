package com.projet.proxibanque_groupe3.service;

import com.projet.proxibanque_groupe3.ProxibanqueGroupe3Application;
import com.projet.proxibanque_groupe3.model.BankAccount;
import com.projet.proxibanque_groupe3.model.CheckingAccount;
import com.projet.proxibanque_groupe3.model.Transfert;
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
            logger.info("Les comptes du clients d'id " + id + " ont été récupérés avec succès de la base de données.");
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return bankAccounts;
    }

    public Optional<BankAccount> getBankAccountByAccountNumber(Integer accountnumber){

        Optional<BankAccount> bankAccount = null;
        try {
            bankAccount = bankAccountRepository.getBankAccountByAccountNumber(accountnumber);
            logger.info("Compte numéro " + accountnumber + " récupéré avec succès.");
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return bankAccount;
    }

    public void makeTransfert(Transfert transfert) throws Exception {

        // Check if the accounts are differents
        if (transfert.getAccountNumberCredited().equals(transfert.getAccountNumberDebited())){
            logger.warn("Virement impossible : les deux numéros de comptes reçus sont identiques.");
            throw new Exception("Les deux numéros de compte indiqués sont identiques.");
        }

        // Check debited account solvability
        BankAccount accountDebited = bankAccountRepository.getBankAccountByAccountNumber(transfert.getAccountNumberDebited()).get();
        if(accountDebited instanceof CheckingAccount c && (c.getBalance() + c.getOverdraft() < transfert.getAmount())){
            logger.warn("Virement impossible : solde insuffisant.");
            throw new Exception("Solde insuffisant pour effectuer un virement.");
        }

        // Transfert
        BankAccount accountCredited = bankAccountRepository.getBankAccountByAccountNumber(transfert.getAccountNumberCredited()).get();
        accountDebited.setBalance(accountDebited.getBalance() - transfert.getAmount());
        accountCredited.setBalance(accountCredited.getBalance() + transfert.getAmount());

        // Call the TransactionService to create the trasactions
        transactionService.createTransaction(accountDebited.getAccountNumber(), "Virement vers compte n°" + accountCredited.getAccountNumber(), transfert.getAmount());
        transactionService.createTransaction(accountCredited.getAccountNumber(), "Virement recu  du compte n°" + accountDebited.getAccountNumber(), transfert.getAmount());
    }
}
