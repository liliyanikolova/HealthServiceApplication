package com.healthserviceapp.areas.protocol.models.bindingModels;

import com.healthserviceapp.areas.medicine.entities.Medicine;
import org.hibernate.validator.constraints.NotEmpty;

public class PrescriptionBindingModel {

    @NotEmpty(message = "Изберете лекарство")
    private String medicine;

    @NotEmpty(message = "Изберете доза")
    private Double doze;

    public PrescriptionBindingModel() {
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public Double getDoze() {
        return doze;
    }

    public void setDoze(Double doze) {
        this.doze = doze;
    }
}
