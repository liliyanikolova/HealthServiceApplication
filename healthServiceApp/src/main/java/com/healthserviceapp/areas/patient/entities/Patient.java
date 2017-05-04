package com.healthserviceapp.areas.patient.entities;

import com.healthserviceapp.areas.protocol.entities.Protocol;
import com.healthserviceapp.areas.users.entities.Doctor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rzok_number")
    private String rzokNumber;

    private String healthService;

    @Column(name = "personal_identification_number")
    private String egn;

    private String firstName;

    private String surname;

    private String lastName;

    @Column(name = "town_village")
    private String town;

    private String address;

    private String gp;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Protocol> protocols;

    public Patient() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRzokNumber() {
        return rzokNumber;
    }

    public void setRzokNumber(String rzokNumber) {
        this.rzokNumber = rzokNumber;
    }

    public String getHealthService() {
        return healthService;
    }

    public void setHealthService(String healthService) {
        this.healthService = healthService;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGp() {
        return gp;
    }

    public void setGp(String gp) {
        this.gp = gp;
    }

    public Set<Protocol> getProtocols() {
        return protocols;
    }

    public void setProtocols(Set<Protocol> protocols) {
        this.protocols = protocols;
    }

}
