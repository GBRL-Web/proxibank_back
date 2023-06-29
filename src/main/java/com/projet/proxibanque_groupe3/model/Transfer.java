package com.projet.proxibanque_groupe3.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Transfer {

    @NotNull(message = "Missing debited account ID.")
    private Integer accountNumberDebited;

    @NotNull(message = "Missing credited account number.")
    private Integer accountNumberCredited;

    @NotNull(message = "Missing transfer amount.")
    @Min(value = 0, message = "Transfer amount cannot be negative.")
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
