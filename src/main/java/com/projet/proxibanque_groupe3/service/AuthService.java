package com.projet.proxibanque_groupe3.service;

import com.projet.proxibanque_groupe3.exceptions.NotFoundException;
import com.projet.proxibanque_groupe3.model.AuthInfos;
import com.projet.proxibanque_groupe3.model.Employee;
import com.projet.proxibanque_groupe3.persistance.AuthRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    private Logger logger = LoggerFactory.getLogger(AuthService.class);

    public AuthInfos createAuthinfos(Employee employee) {
        String temp = Character.toLowerCase(employee.getSurname().charAt(0)) + employee.getName().toLowerCase();
        return new AuthInfos(temp, temp);
    }

    public Employee checkCredentials(AuthInfos authInfosToTest) throws NotFoundException {

        // Search for the AuthInfo with this login in the database
        Optional<AuthInfos> authInfos = authRepository.findByLogin(authInfosToTest.getLogin());
        if (authInfos.isEmpty()) {
            logger.error("Failed login attempt: unknown login {}.", authInfosToTest.getLogin());
            throw new NotFoundException("Unknown login attempt...");
        }

        // Check if the passwords match
        if (!authInfos.get().getPassword().equals(authInfosToTest.getPassword())) {
            logger.error("Failed login attempt: incorrect password for account {}.",
                    authInfosToTest.getLogin());
            throw new NotFoundException("Authentication failed.");
        }

        logger.info("Employee {} {} has just logged in.", authInfos.get().getEmployee().getName(),
                authInfos.get().getEmployee().getSurname());
        // Return the corresponding Employee
        return authInfos.get().getEmployee();
    }
}
