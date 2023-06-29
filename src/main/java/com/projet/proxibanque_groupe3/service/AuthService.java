package com.projet.proxibanque_groupe3.service;

import com.projet.proxibanque_groupe3.ProxibanqueGroupe3Application;
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

    private Logger logger = LoggerFactory.getLogger(ProxibanqueGroupe3Application.class);

    public AuthInfos createAuthinfos(Employee employee) {
        String temp = Character.toLowerCase(employee.getSurname().charAt(0)) + employee.getName().toLowerCase();
        AuthInfos authInfos = new AuthInfos(temp, temp);
        return authInfos;
    }

    public Employee checkCredentials(AuthInfos authInfosToTest) throws Exception {

        // On recherche l'AuthInfo ayant ce login en bdd
        Optional<AuthInfos> authInfos = authRepository.findByLogin(authInfosToTest.getLogin());
        if (authInfos.isEmpty()) {
            logger.error("Failed login attempt: unknown login %s.", authInfosToTest.getLogin());
            throw new Exception("Unknown login attempt...");
        }

        // On vérifie que les password correspondent
        if (!authInfos.get().getPassword().equals(authInfosToTest.getPassword())) {
            logger.error("Tentative de connexion échouée : mot de passe pour le compte " + authInfosToTest.getLogin() + " erroné.");
            throw new Exception("Authentification échouée.");
        }

        logger.info("L'employé " +
                authInfos.get().getEmployee().getName() + " " +
                authInfos.get().getEmployee().getSurname() + " vient de se connecter.");
        // On retourne l'Employee correspondant
        return authInfos.get().getEmployee();
    }
}
