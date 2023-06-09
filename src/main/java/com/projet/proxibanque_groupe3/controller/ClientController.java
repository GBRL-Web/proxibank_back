package com.projet.proxibanque_groupe3.controller;

import com.projet.proxibanque_groupe3.model.Client;
import com.projet.proxibanque_groupe3.service.ClientService;
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
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

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
    @GetMapping("/counselor/{id}")
    public ResponseEntity<Set<Client>> getClients(@PathVariable Integer id){
        Optional<Set<Client>> clients = clientService.getClientByCounselorId(id);
        if(clients.isPresent()){
            return ResponseEntity.ok().body(clients.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping
    public ResponseEntity<Client> putClient(@Valid @RequestBody Client newClient){
        clientService.editClient(newClient);
        return ResponseEntity.ok().build();
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/counselors/{idCounselor}", consumes = "application/json")
    public ResponseEntity<Client> postClient(@PathVariable Integer idCounselor, @Valid @RequestBody Client newClient) {
        Client createdClient = clientService.createClient(idCounselor, newClient);
        if (createdClient == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdClient);
    }
}
