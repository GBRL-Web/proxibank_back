package com.projet.proxibanque_groupe3.service;

import com.projet.proxibanque_groupe3.model.BankAccount;
import com.projet.proxibanque_groupe3.model.CheckingAccount;
import com.projet.proxibanque_groupe3.model.Client;
import com.projet.proxibanque_groupe3.model.Employee;
import com.projet.proxibanque_groupe3.model.SavingAccount;
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
            logger.info("The list of clients for advisor with ID {} has been successfully retrieved.", id);
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
            CheckingAccount checkingAccount = new CheckingAccount(100F, 50F);
            SavingAccount savingAccount = new SavingAccount(50F, 3F);
            newClient.addBankAccount(checkingAccount);
            newClient.addBankAccount(savingAccount);
            checkingAccount.setClient(newClient);
            savingAccount.setClient(newClient);
            logger.info("New client successfully created.");
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

public boolean deleteClient(Integer clientId) {
    try {
        Client client = this.clientRepository.findById((long) clientId).orElse(null);
        if (client != null) {
            Set<BankAccount> bankAccounts = client.getBankAccounts();
            for (BankAccount account : bankAccounts) {
                account.setClient(null);
            }
            client.clearBankAccounts();
            this.clientRepository.delete(client);
            logger.info("Client with ID {} and associated accounts deleted successfully.", clientId);
            return true;
        } else {
            logger.warn("Client not found for ID: {}", clientId);
            return false;
        }
    } catch (Exception e) {
        logger.error(e.getMessage());
        return false;
    }
}
}
