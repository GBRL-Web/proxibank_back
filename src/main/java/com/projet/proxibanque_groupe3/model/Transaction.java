package com.projet.proxibanque_groupe3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    private Integer accountNumber;

    private String description;

    private Float amount;

    public Transaction() {
    }

    public Transaction(LocalDate date, Integer accountNumber, String description, Float amount) {
        this.date = date;
        this.accountNumber = accountNumber;
        this.description = description;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Integer getAccounNumber() {
        return accountNumber;
    }

    public void setAccounNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
