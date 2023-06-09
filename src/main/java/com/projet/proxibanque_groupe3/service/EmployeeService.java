package com.projet.proxibanque_groupe3.service;

import com.projet.proxibanque_groupe3.ProxibanqueGroupe3Application;
import com.projet.proxibanque_groupe3.model.Employee;
import com.projet.proxibanque_groupe3.persistance.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Logger logger = LoggerFactory.getLogger(ProxibanqueGroupe3Application.class);

    public Optional<Set<Employee>> getCounselorsFromDatabase(){
        Optional<Set<Employee>> counselors = null;
        try {
            counselors = employeeRepository.findByClientsNotNull();
            logger.info("Liste des conseillers récupérée avec succès.");
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return counselors;
    }
}
