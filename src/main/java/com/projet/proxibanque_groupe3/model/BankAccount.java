package com.projet.proxibanque_groupe3.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class BankAccount {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "type", insertable = false, updatable = false)
    private String type;

    private static Integer nbAccount = 1;

    private Integer accountNumber;

    private Float balance;

    private final LocalDate openedAt = LocalDate.now();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="client_id")
    private Client client;

    public BankAccount() {
    }

    public BankAccount(Float balance) {
        this.accountNumber = nbAccount;
        this.balance = balance;
        nbAccount++;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public LocalDate getOpenedAt() {
        return openedAt;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getType() {
        return type;
    }
}
