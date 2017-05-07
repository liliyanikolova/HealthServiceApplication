package com.healthserviceapp.areas.medicine.models.bindingModels;

import com.healthserviceapp.areas.medicine.customValidation.IsCodeUnique;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class AddMedicineBindingModel {

    @IsCodeUnique
    @Size(min = 5, max = 5, message = "Невалиден код")
    @NotEmpty(message = "Въведете код")
    private String code;

    @NotEmpty(message = "Въведете наименование")
    private String name;

    @NotEmpty(message = "Въведете мерна единица")
    private String measurement;

    @Size(min = 1, message = "Въведете поне една доза")
    private Integer[] dozes;

    public AddMedicineBindingModel() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public Integer[] getDozes() {
        return dozes;
    }

    public void setDozes(Integer[] doze) {
        this.dozes = doze;
    }
}
