package com.healthserviceapp.areas.patient.models.bindingModels;

import org.hibernate.validator.constraints.NotEmpty;

public class EditPatientBindingModel {

    private Long id;

    @NotEmpty(message = "Въведете РЗОК No.")
    private String rzokNumber;

    @NotEmpty(message = "Въведете здравен район")
    private String healthService;

    private String egn;

    @NotEmpty(message = "Въведете име")
    private String firstName;

    @NotEmpty(message = "Въведете презиме")
    private String surname;

    @NotEmpty(message = "Въведете фамилия")
    private String lastName;

    @NotEmpty(message = "Въведете град/село")
    private String town;

    @NotEmpty(message = "Въведете адрес")
    private String address;

    @NotEmpty(message = "Въведете име на личен лекар")
    private String gp;

    public EditPatientBindingModel() {
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
}
