package com.projet.proxibanque_groupe3.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Transfer {

    @NotNull(message = "Id du compte débité manquant.")
    private Integer accountNumberDebited;

    @NotNull(message = "Numéro de compte du compte crédité manquant.")
    private Integer accountNumberCredited;

    @NotNull(message = "Montant du transfert manquant.")
    @Min(value = 0, message = "Le montant du transfert ne peut être négatif.")
    private Float amount;

    public Transfer(Integer accountNumberDebited, Integer accountNumberCredited, Float amount) {
        this.accountNumberDebited = accountNumberDebited;
        this.accountNumberCredited = accountNumberCredited;
        this.amount = amount;
    }

    public Integer getFromAccount() {
        return accountNumberDebited;
    }

    public void setFromAccount(Integer accountNumberDebited) {
        this.accountNumberDebited = accountNumberDebited;
    }

    public Integer getToAccount() {
        return accountNumberCredited;
    }

    public void setToAccount(Integer accountNumberCredited) {
        this.accountNumberCredited = accountNumberCredited;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
