package com.projet.proxibanque_groupe3.service;

import com.projet.proxibanque_groupe3.model.Client;
import com.projet.proxibanque_groupe3.model.Employee;
import com.projet.proxibanque_groupe3.persistance.ClientRepository;
import com.projet.proxibanque_groupe3.persistance.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmployeeRepository employeRepository;

    private Logger logger = LoggerFactory.getLogger(ClientService.class);

    public Optional<Set<Client>> getClientByCounselorId(Integer id) {

        try {
            Optional<Set<Client>> clients = this.clientRepository.getByCounselorId((long) id);
            logger.info("Liste des clients du conseiller d'id {} récupérée avec succès.", id);
            return clients;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

public Client createClient(Integer idCounselor, Client newClient) {
    try {
        Employee counselor = this.employeRepository.findById((long) idCounselor).orElse(null);
        if (counselor != null) {
            newClient.setCounselor(counselor);
            counselor.addClient(newClient);
            return this.clientRepository.save(newClient);
        } else {
            // Handle the case when counselor is not found
            logger.warn("Counselor not found for ID: {}", idCounselor);
            return null;
        }
    } catch (Exception e) {
        logger.error(e.getMessage());
        return null;
    }
}

public void editClient(Client newClient) {
    clientRepository.findById(newClient.getId()).ifPresent(client -> {
        newClient.setCounselor(client.getCounselor());
        try {
            clientRepository.save(newClient);
            logger.info("The client with ID {} has been successfully modified.", newClient.getId());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    });
}
}
