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

    @Pattern(regexp = "^[A-Za-zÀ-ÿ]+$", message = "The first name field can only contain letters.")
    @NotEmpty(message = "The first name field cannot be empty.")
    private String name;

    @Pattern(regexp = "^[A-Za-zÀ-ÿ]+$", message = "The last name field can only contain letters.")
    @NotEmpty(message = "The last name field cannot be empty.")
    private String surname;

    @Pattern(regexp = "[0-9A-Za-zÀ-ÿ '-]+$", message = "The address field can only contain numbers, letters, and spaces.")
    @NotEmpty(message = "The address field cannot be empty.")
    private String address;

    @Pattern(regexp = "^[0-9]+$", message = "The zip code field can only contain numbers.")
    @NotEmpty(message = "The zip code field cannot be empty.")
    private String zip;

    @Pattern(regexp = "[A-Za-zÀ-ÿ '-]+$", message = "The city field can only contain letters.")
    @NotEmpty(message = "The city field cannot be empty.")
    private String city;

    @Pattern(regexp = "^[0-9]+$", message = "The telephone field can only contain numbers.")
    @NotEmpty(message = "The telephone field cannot be empty.")
    private String tel;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "counselor_id")
    private Employee counselor;

    @OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
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

    public void addBankAccount(BankAccount bankAccount) {
        this.bankAccounts.add(bankAccount);
    }

    public Set<BankAccount> getBankAccounts() {
        return this.bankAccounts;
    }

    public void clearBankAccounts() {
        this.bankAccounts = new HashSet<>();
    }
}
