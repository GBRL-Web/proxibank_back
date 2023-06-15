package com.projet.proxibanque_groupe3.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Active")
public class CheckingAccount extends BankAccount {
    private enum card {
        VISA,
        ELECTRON
    }

    private Float overdraft;

    private String cardType;

    public CheckingAccount() {
    }

    public CheckingAccount(Float balance, Float overdraft) {
        super(balance);
        this.overdraft = overdraft;
        this.cardType = card.ELECTRON.toString();
    }

    public Float getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(Float overdraft) {
        this.overdraft = overdraft;
    }

    public String getCardType() {
        return cardType;
    }

    public void upgradeCard(){
        this.cardType = card.VISA.toString();
    }
}
