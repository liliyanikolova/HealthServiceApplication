package com.healthserviceapp.areas.users.entities;

import com.healthserviceapp.areas.patient.entities.Patient;
import com.healthserviceapp.areas.workplace.entities.DoctorWorkplace;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue("doctor_user")
public class Doctor extends User{

    private String uin;

    @ManyToOne
    @JoinColumn(name = "titles")
    private Title title;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "workplace_id", referencedColumnName = "id"))
    private Set<DoctorWorkplace> workplaces;

    //TODO
//    private ... picture;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id", referencedColumnName = "id"))
    private Set<Speciality> specialities;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "doctor_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"))
    private Set<Patient> patients;

    public Doctor() {
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<DoctorWorkplace> getWorkplaces() {
        return workplaces;
    }

    public void setWorkplaces(Set<DoctorWorkplace> workplaces) {
        this.workplaces = workplaces;
    }

    public Set<Speciality> getSpecialities() {
        return this.specialities;
    }

    public void setSpecialities(Set<Speciality> specialities) {
        this.specialities = specialities;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

}
