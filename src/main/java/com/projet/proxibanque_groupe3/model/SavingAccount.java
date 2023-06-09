package com.projet.proxibanque_groupe3.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Compte épargne")
public class SavingAccount extends BankAccount {

    private Float rate;

    public SavingAccount() {
    }

    public SavingAccount(Float balance, Float rate) {
        super(balance);
        this.rate = rate;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}
