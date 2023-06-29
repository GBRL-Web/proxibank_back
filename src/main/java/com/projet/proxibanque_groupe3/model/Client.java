package com.projet.proxibanque_groupe3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp="^[A-Za-zÀ-ÿ]+$", message="Le champ prénom ne peut contenir que des lettres.")
    @NotEmpty(message = "Le champ prénom ne peut être vide.")
    private String name;

    @Pattern(regexp="^[A-Za-zÀ-ÿ]+$", message="Le champ nom ne peut contenir que des lettres.")
    @NotEmpty(message = "Le champ nom ne peut être vide.")
    private String surname;

    @Pattern(regexp="[0-9A-Za-zÀ-ÿ '-]+$", message="Le champ adresse ne peut contenir que des des chiffres, des lettres et des espaces.")
    @NotEmpty(message = "Le champ adresse ne peut être vide.")
    private String address;

    @Pattern(regexp="^[0-9]+$", message="Le champ code postal ne peut contenir que des chiffres.")
    @NotEmpty(message = "Le champ code postal ne peut être vide.")
    private String zip;

    @Pattern(regexp="[A-Za-zÀ-ÿ '-]+$", message="Le champ ville ne peut contenir que des lettres.")
    @NotEmpty(message = "Le champ ville ne peut être vide.")
    private String city;

    @Pattern(regexp="^[0-9]+$", message="Le champ téléphone ne peut contenir que des chiffres.")
    @NotEmpty(message = "Le champ téléphone ne peut être vide.")
    private String tel;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="counselor_id")
    private Employee counselor;

    @OneToMany(mappedBy="client", cascade = CascadeType.PERSIST)
    private Set<BankAccount> bankAccounts = new HashSet<>();

    public Client() {
    }

    public Client(String name, String surname, String address, String zip, String city, String tel) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.zip = zip;
        this.city = city;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Long getId() {
        return id;
    }

    public Employee getCounselor() {
        return counselor;
    }

    public void setCounselor(Employee counselor) {
        this.counselor = counselor;
    }

    public void addBankAccount(BankAccount bankAccount){
        this.bankAccounts.add(bankAccount);
    }
}
