package com.projet.proxibanque_groupe3.controller;

import com.projet.proxibanque_groupe3.model.BankAccount;
import com.projet.proxibanque_groupe3.model.Transfer;
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
@RequestMapping("/accounts")
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

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("client/{id}")
    public ResponseEntity<Set<BankAccount>> getBankAccountsByIdClient(@PathVariable Integer id){
        Optional<Set<BankAccount>> bankAccounts = bankAccountService.getBankAccountsByIdClientFromDatabase(id);
        if(bankAccounts.isPresent()){
            return ResponseEntity.ok().body(bankAccounts.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("all-accts/{accNum}")
    public ResponseEntity<BankAccount> getBankAccountByAccountNumber(@PathVariable Integer accNum){
        Optional<BankAccount> bankAccount = bankAccountService.getBankAccountByAccountNumber(accNum);
        if (bankAccount.isPresent()){
            return ResponseEntity.ok().body(bankAccount.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("transfer")
    public ResponseEntity<String> postTransfer(@Valid @RequestBody Transfer transfer){
        try {
            bankAccountService.transferTo(transfer);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
