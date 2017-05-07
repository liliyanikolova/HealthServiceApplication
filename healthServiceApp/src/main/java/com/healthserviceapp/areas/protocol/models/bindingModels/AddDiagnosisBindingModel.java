package com.healthserviceapp.areas.protocol.models.bindingModels;

import org.hibernate.validator.constraints.NotEmpty;

public class AddDiagnosisBindingModel {

    @NotEmpty(message = "Въведете код")
    private String code;

    @NotEmpty(message = "Въведете наименование на заболяването")
    private String description;

    public AddDiagnosisBindingModel() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
