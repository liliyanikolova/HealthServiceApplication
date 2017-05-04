package com.healthserviceapp.areas.patient.models.bindingModels;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class SearchPatientBidingModel {

    @NotEmpty(message = "Въведете ЕГН")
    @Size(min = 10, max = 10, message = "Невалидно ЕГН")
    private String egn;

    public SearchPatientBidingModel() {
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }
}
