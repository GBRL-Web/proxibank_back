package com.projet.proxibanque_groupe3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class AuthInfos {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "Nom d'utilisateur manquant.")
    private String login;

    @NotEmpty(message = "Mot de passe manquant.")
    private String password;

    public AuthInfos() {
    }

    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public AuthInfos(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
