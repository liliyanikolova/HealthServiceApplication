package com.healthserviceapp.areas.medicine.entities;

import com.healthserviceapp.areas.protocol.entities.Prescription;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.SortedSet;

@Entity
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval=true)
    private Set<Doze> dozes;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Prescription> prescriptions;

    public Medicine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Doze> getDozes() {
        return dozes;
    }

    public void setDozes(Set<Doze> dozes) {
        this.dozes = dozes;
    }

    public Set<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Set<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
