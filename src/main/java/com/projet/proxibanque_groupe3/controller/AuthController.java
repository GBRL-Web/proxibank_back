package com.projet.proxibanque_groupe3.controller;

import com.projet.proxibanque_groupe3.model.AuthInfos;
import com.projet.proxibanque_groupe3.model.Employee;
import com.projet.proxibanque_groupe3.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException e){
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
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody AuthInfos authInfos){
        Employee employee = null;
        try {
            employee = authService.checkCredentials(authInfos);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(employee);
    }
}
