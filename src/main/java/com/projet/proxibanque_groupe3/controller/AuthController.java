package com.projet.proxibanque_groupe3.controller;

import com.projet.proxibanque_groupe3.model.AuthInfos;
import com.projet.proxibanque_groupe3.model.Employee;
import com.projet.proxibanque_groupe3.responder.LoginResponse;
import com.projet.proxibanque_groupe3.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody AuthInfos authInfos){
        LoginResponse response = new LoginResponse();
        try {
            Employee employee = authService.checkCredentials(authInfos);
        response.setEmployee(employee);
        } catch (Exception e){
            response.setErrorMessage(e.getMessage());
        }
        return response;
    }
}
