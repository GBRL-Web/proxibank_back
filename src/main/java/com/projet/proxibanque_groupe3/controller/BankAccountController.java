package com.projet.proxibanque_groupe3.controller;

import com.projet.proxibanque_groupe3.model.BankAccount;
import com.projet.proxibanque_groupe3.model.Transfert;
import com.projet.proxibanque_groupe3.service.BankAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/bankaccounts")
public class BankAccountController {

    @Autowired
    private BankAccountService bankAccountService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> hendleValidationExceptions(MethodArgumentNotValidException e){

        HashMap<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach(
                element -> {
                    String fieldName = ((FieldError)element).getField();
                    String errorMessage = element.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                }
        );

        return errors;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/client/{id}")
    public ResponseEntity<Set<BankAccount>> getBankAccountsByIdClient(@PathVariable Integer id){
        Optional<Set<BankAccount>> bankAccounts = bankAccountService.getBankAccountsByIdClientFromDatabase(id);
        if(bankAccounts.isPresent()){
            return ResponseEntity.ok().body(bankAccounts.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/accountnumber/{accountNumber}")
    public ResponseEntity<BankAccount> getBankAccountByAccountNumber(@PathVariable Integer accountNumber){
        Optional<BankAccount> bankAccount = bankAccountService.getBankAccountByAccountNumber(accountNumber);
        if (bankAccount.isPresent()){
            return ResponseEntity.ok().body(bankAccount.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/transfert")
    public ResponseEntity postMakeTransfert(@Valid @RequestBody Transfert transfert){
        try {
            bankAccountService.makeTransfert(transfert);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
