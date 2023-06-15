package com.projet.proxibanque_groupe3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String surname;

    private String role;

    // Début de la relation entre objets de la même table

    @ManyToOne
    @JsonIgnore
    private Employee director;

    @JsonIgnore
    @OneToMany(mappedBy="director", cascade = CascadeType.PERSIST)
    private Set<Employee> counselors = new HashSet<Employee>();

    // Fin de la relation entre objets de la même table

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "agency_id")
    private Agency agency;

    @JsonIgnore
    @OneToOne(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private AuthInfos authInfos;

    @JsonIgnore
    @OneToMany(mappedBy="counselor", cascade = CascadeType.PERSIST)
    private Set<Client> clients = new HashSet<Client>();

    public Employee() {
    }

    public Employee(String name, String surname, String role) {
        this.name = name;
        this.surname = surname;
        this.role = role;

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

    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Employee getDirector() {
        return director;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }

    public Set<Employee> getCounselors() {
        return counselors;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void addCounselor(Employee counselor){
        this.counselors.add(counselor);
    }

    public void addClient(Client client){
        this.clients.add(client);
    }

    public void setAuthInfos(AuthInfos authInfos) {
        this.authInfos = authInfos;
    }
}
