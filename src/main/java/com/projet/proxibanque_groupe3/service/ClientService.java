package com.projet.proxibanque_groupe3.service;

import com.projet.proxibanque_groupe3.ProxibanqueGroupe3Application;
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

    private Logger logger = LoggerFactory.getLogger(ProxibanqueGroupe3Application.class);

    public Optional<Set<Client>> getClientByCounselorId(Integer id){

        Optional<Set<Client>> clients = null;
        try {
            clients= this.clientRepository.getClientsByCounselor_Id((long)id);
            logger.info("Liste des clients du conseiller d'id " + id + " récupérée avec succès.");
        } catch ( Exception e){
            logger.error(e.getMessage());
        }
        return clients;
    }

    public Client createClient(Integer idCounselor, Client newClient) {
        try {
            Employee counselor = this.employeRepository.findById((long) idCounselor).get();
            newClient.setCounselor(counselor);
            counselor.addClient(newClient);
            Client createdClient = this.clientRepository.save(newClient);
            return createdClient;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null; 
        }
    }
    

    public void editClient(Client newClient){
        Client client = clientRepository.findById(newClient.getId()).get();
        newClient.setCounselor(client.getCounselor());

        try {
            clientRepository.save(newClient);
            logger.info("Le client d'id " + newClient.getId() + " a été modifié avec succès.");
        } catch ( Exception e){
            logger.error(e.getMessage());
        }
    }
}
