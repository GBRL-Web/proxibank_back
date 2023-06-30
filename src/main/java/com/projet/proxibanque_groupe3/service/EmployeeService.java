package com.projet.proxibanque_groupe3.service;

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

    private Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public Optional<Set<Employee>> getCounselorsFromDatabase(){
        try {
            Optional<Set<Employee>> counselors = employeeRepository.findByClientsNotNull();
            logger.info("Counselors list successfully retrieved.");
            return counselors;
        } catch (Exception e){
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }
}
