package com.healthserviceapp.areas.protocol.entities;

import com.healthserviceapp.areas.medicine.entities.Medicine;

import javax.persistence.*;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;

    private Integer doze;

    @ManyToOne
    @JoinColumn(name = "protocol_id")
    private Protocol protocol;

    public Prescription() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Integer getDoze() {
        return doze;
    }

    public void setDoze(Integer doze) {
        this.doze = doze;
    }
}
