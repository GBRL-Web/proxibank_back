package com.projet.proxibanque_groupe3.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Agency {

    @Id
    @GeneratedValue
    private Long id;

    private static Integer nbAgency = 1;

    private Integer agencyNumber;

    private String name;

    private final LocalDate openedAt = LocalDate.now();

    @OneToOne(mappedBy = "agency", cascade = CascadeType.PERSIST)
    private Employee director;

    public Agency() {
    }

    public Agency(String name) {
        this.agencyNumber = nbAgency;
        this.name = name;
        nbAgency++;
    }

    public Integer getAgencyNumber() {
        return agencyNumber;
    }

    public void setAgencyNumber(Integer agencyNumber) {
        this.agencyNumber = agencyNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getOpenedAt() {
        return openedAt;
    }

    public Long getId() {
        return id;
    }

    public Employee getDirector() {
        return director;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }
}
