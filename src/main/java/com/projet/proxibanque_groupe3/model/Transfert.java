package com.projet.proxibanque_groupe3.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Transfert {

    @NotNull(message = "Id du compte débité manquant.")
    private Integer accountNumberDebited;

    @NotNull(message = "Numéro de compte du compte crédité manquant.")
    private Integer accountNumberCredited;

    @NotNull(message = "Montant du transfert manquant.")
    @Min(value = 0, message = "Le montant du transfert ne peut être négatif.")
    private Float amount;

    public Transfert(Integer accountNumberDebited, Integer accountNumberCredited, Float amount) {
        this.accountNumberDebited = accountNumberDebited;
        this.accountNumberCredited = accountNumberCredited;
        this.amount = amount;
    }

    public Integer getAccountNumberDebited() {
        return accountNumberDebited;
    }

    public void setAccountNumberDebited(Integer accountNumberDebited) {
        this.accountNumberDebited = accountNumberDebited;
    }

    public Integer getAccountNumberCredited() {
        return accountNumberCredited;
    }

    public void setAccountNumberCredited(Integer accountNumberCredited) {
        this.accountNumberCredited = accountNumberCredited;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
