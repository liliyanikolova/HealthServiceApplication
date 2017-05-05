package com.healthserviceapp.areas.medicine.models.bindingModels;

import org.hibernate.validator.constraints.NotEmpty;

public class AddDozeBidingModel {

    @NotEmpty(message = "Въведете количестов")
    private Integer quantity;

    @NotEmpty(message = "Въведете мерна единица")
    private String measurement;

    public AddDozeBidingModel() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
