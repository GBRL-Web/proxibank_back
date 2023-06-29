package com.projet.proxibanque_groupe3.controller;

import com.projet.proxibanque_groupe3.model.Employee;
import com.projet.proxibanque_groupe3.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/counselors")
    public ResponseEntity<Set<Employee>> getClients(){
        Optional<Set<Employee>> counselors = employeeService.getCounselorsFromDatabase();
        if(counselors.isPresent()){
            return ResponseEntity.ok().body(counselors.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
