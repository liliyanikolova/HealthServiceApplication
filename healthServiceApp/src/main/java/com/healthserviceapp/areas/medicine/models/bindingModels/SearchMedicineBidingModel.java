package com.healthserviceapp.areas.medicine.models.bindingModels;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class SearchMedicineBidingModel {

    @Size(min = 5, max = 5, message = "Невалиден код")
    @NotEmpty(message = "Въведете код")
    private String code;

    public SearchMedicineBidingModel() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
