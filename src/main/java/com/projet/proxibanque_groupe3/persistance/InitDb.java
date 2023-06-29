package com.projet.proxibanque_groupe3.persistance;

import com.projet.proxibanque_groupe3.model.*;
import com.projet.proxibanque_groupe3.service.AuthService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class InitDb {

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AuthService authService;

    @PostConstruct
    public void initDb() {

        // Création du personnel de l'agence
        Agency agency = new Agency("Agence Tout risque");
        Employee director = new Employee("Bonneau", "Jean", "director");
        Employee counselor1 = new Employee("Peuplu", "Jean", "counselor");
        Employee counselor2 = new Employee("Aimarre", "Jean", "counselor");
        Employee counselor3 = new Employee("Serien", "Jean", "counselor");

        // Création et affectation des logins/mdp
        AuthInfos authInfosDirector = authService.createAuthinfos(director);
        AuthInfos authInfosCounselor1 = authService.createAuthinfos(counselor1);
        AuthInfos authInfosCounselor2 = authService.createAuthinfos(counselor2);
        AuthInfos authInfosCounselor3 = authService.createAuthinfos(counselor3);
        director.setAuthInfos(authInfosDirector);
        authInfosDirector.setEmployee(director);
        counselor1.setAuthInfos(authInfosCounselor1);
        authInfosCounselor1.setEmployee(counselor1);
        counselor2.setAuthInfos(authInfosCounselor2);
        authInfosCounselor2.setEmployee(counselor2);
        counselor3.setAuthInfos(authInfosCounselor3);
        authInfosCounselor3.setEmployee(counselor3);

        // Création des clients
Client client1 = new Client("Racine", "Jean", "10 Rue de la Liberté", "75001", "Paris", "0123456780");
Client client2 = new Client("Fonce", "Paul", "25 Avenue des Champs-Élysées", "75008", "Paris", "0678901231");
Client client3 = new Client("Saigne", "Marie", "5 Rue du Commerce", "69002", "Lyon", "0456789122");
Client client4 = new Client("Foutpaune", "Claire", "12 Rue des Lilas", "33000", "Bordeaux", "0567891233");
Client client5 = new Client("Balle", "Pierre", "8 Place de la République", "44000", "Nantes", "0789123454");
Client client6 = new Client("Bonbeurre", "Luc", "15 Rue du Vieux Port", "13001", "Marseille", "0891234565");
Client client7 = new Client("Tanrien", "Sophie", "20 Rue des Roses", "59000", "Lille", "0123456786");
Client client8 = new Client("Conépa", "Thomas", "3 Avenue des Acacias", "67000", "Strasbourg", "0678901237");
Client client9 = new Client("Terre", "Emma", "6 Rue des Artisans", "54000", "Nancy", "0456789128");
Client client10 = new Client("Tanleloulerenaretlabelette", "Alex", "18 Rue de la Paix", "69003", "Lyon", "0567891239");
Client client11 = new Client("Transene", "Laura", "9 Boulevard Saint-Michel", "75005", "Paris", "0678901230");
Client client12 = new Client("Tissipe", "David", "14 Avenue de la Gare", "67000", "Strasbourg", "0789123451");
Client client13 = new Client("Tanlamer", "Sophia", "22 Rue du Pont Neuf", "31000", "Toulouse", "0891234562");
Client client14 = new Client("Peste", "Hugo", "11 Rue des Violettes", "06000", "Nice", "0123456783");
Client client15 = new Client("Darmerie", "Julie", "7 Place de l'Église", "35000", "Rennes", "0678901234");
Client client16 = new Client("Cive", "Antoine", "4 Avenue de la Libération", "44000", "Nantes", "0456789125");
Client client17 = new Client("Breille", "Manon", "13 Rue des Moulins", "69004", "Lyon", "0567891236");


        // Création des comptes
        CheckingAccount checkingAccount1 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount1 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount2 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount2 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount3 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount3 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount4 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount4 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount5 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount5 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount6 = new CheckingAccount(15000F, 500F);
        SavingAccount savingAccount6 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount7 = new CheckingAccount(15000F, 500F);
        SavingAccount savingAccount7 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount8 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount8 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount9 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount9 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount10 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount10 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount11 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount11 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount12 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount12 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount13 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount13 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount14 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount14 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount15 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount15 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount16 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount16 = new SavingAccount(2000F, 2F);
        CheckingAccount checkingAccount17 = new CheckingAccount(5000F, 500F);
        SavingAccount savingAccount17 = new SavingAccount(2000F, 2F);

        // Affection du directeur à l'agence
        agency.setDirector(director);
        director.setAgency(agency);

        // Affectation des conseillers au directeur
        director.addCounselor(counselor1);
        counselor1.setDirector(director);
        director.addCounselor(counselor2);
        counselor2.setDirector(director);
        director.addCounselor(counselor3);
        counselor3.setDirector(director);

        // Affectation des clients aux conseillers
        director.addClient(client1);
        client1.setCounselor(director);
        counselor1.addClient(client2);
        client2.setCounselor(counselor1);
        counselor1.addClient(client3);
        client3.setCounselor(counselor1);
        counselor1.addClient(client4);
        client4.setCounselor(counselor1);
        counselor1.addClient(client5);
        client5.setCounselor(counselor1);
        counselor1.addClient(client6);
        client6.setCounselor(counselor1);
        counselor1.addClient(client7);
        client7.setCounselor(counselor1);

        counselor2.addClient(client8);
        client8.setCounselor(counselor2);
        counselor2.addClient(client9);
        client9.setCounselor(counselor2);
        counselor2.addClient(client10);
        client10.setCounselor(counselor2);
        counselor2.addClient(client11);
        client11.setCounselor(counselor2);
        counselor2.addClient(client12);
        client12.setCounselor(counselor2);
        counselor2.addClient(client13);
        client13.setCounselor(counselor2);

        counselor3.addClient(client14);
        client14.setCounselor(counselor3);
        counselor3.addClient(client15);
        client15.setCounselor(counselor3);
        counselor3.addClient(client16);
        client16.setCounselor(counselor3);
        counselor3.addClient(client17);
        client17.setCounselor(counselor3);

        // Affectation des comptes
        client1.addBankAccount(checkingAccount1);
        client1.addBankAccount(savingAccount1);
        checkingAccount1.setClient(client1);
        savingAccount1.setClient(client1);

        client2.addBankAccount(checkingAccount2);
        client2.addBankAccount(savingAccount2);
        checkingAccount2.setClient(client2);
        savingAccount2.setClient(client2);

        client3.addBankAccount(checkingAccount3);
        client3.addBankAccount(savingAccount3);
        checkingAccount3.setClient(client3);
        savingAccount3.setClient(client3);

        client4.addBankAccount(checkingAccount4);
        client4.addBankAccount(savingAccount4);
        checkingAccount4.setClient(client4);
        savingAccount4.setClient(client4);

        client5.addBankAccount(checkingAccount5);
        client5.addBankAccount(savingAccount5);
        checkingAccount5.setClient(client5);
        savingAccount5.setClient(client5);

        client6.addBankAccount(checkingAccount6);
        client6.addBankAccount(savingAccount6);
        checkingAccount6.setClient(client6);
        savingAccount6.setClient(client6);

        client7.addBankAccount(checkingAccount7);
        client7.addBankAccount(savingAccount7);
        checkingAccount7.setClient(client7);
        savingAccount7.setClient(client7);

        client8.addBankAccount(checkingAccount8);
        client8.addBankAccount(savingAccount8);
        checkingAccount8.setClient(client8);
        savingAccount8.setClient(client8);

        client9.addBankAccount(checkingAccount9);
        client9.addBankAccount(savingAccount9);
        checkingAccount9.setClient(client9);
        savingAccount9.setClient(client9);

        client10.addBankAccount(checkingAccount10);
        client10.addBankAccount(savingAccount10);
        checkingAccount10.setClient(client10);
        savingAccount10.setClient(client10);

        client11.addBankAccount(checkingAccount11);
        client11.addBankAccount(savingAccount11);
        checkingAccount11.setClient(client11);
        savingAccount11.setClient(client11);

        client12.addBankAccount(checkingAccount12);
        client12.addBankAccount(savingAccount12);
        checkingAccount12.setClient(client12);
        savingAccount12.setClient(client12);

        client13.addBankAccount(checkingAccount13);
        client13.addBankAccount(savingAccount13);
        checkingAccount13.setClient(client13);
        savingAccount13.setClient(client13);

        client14.addBankAccount(checkingAccount14);
        client14.addBankAccount(savingAccount14);
        checkingAccount14.setClient(client14);
        savingAccount14.setClient(client14);

        client15.addBankAccount(checkingAccount15);
        client15.addBankAccount(savingAccount15);
        checkingAccount15.setClient(client15);
        savingAccount15.setClient(client15);

        client16.addBankAccount(checkingAccount16);
        client16.addBankAccount(savingAccount16);
        checkingAccount16.setClient(client16);
        savingAccount16.setClient(client16);

        client17.addBankAccount(checkingAccount17);
        client17.addBankAccount(savingAccount17);
        checkingAccount17.setClient(client17);
        savingAccount17.setClient(client17);

        // Persist
        agencyRepository.save(agency);

        // Create transactions
        Transaction transaction1 = new Transaction(LocalDate.now(), checkingAccount1.getAccountNumber(),
                "Virement vers compte n°" + savingAccount1.getAccountNumber(), 200F);
        Transaction transaction2 = new Transaction(LocalDate.now(), savingAccount1.getAccountNumber(),
                "Virement depuis compte n°" + checkingAccount1.getAccountNumber(), 200F);
        Transaction transaction3 = new Transaction(LocalDate.parse("2023-05-01"), checkingAccount3.getAccountNumber(),
                "Retrait", 500F);
        Transaction transaction4 = new Transaction(LocalDate.parse("2023-05-01"), checkingAccount8.getAccountNumber(),
                "Dépot", 100F);

        // persist
        transactionRepository.saveAll(List.of(transaction1, transaction2, transaction3, transaction4));
    }
}
