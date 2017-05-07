package com.healthserviceapp.areas.medicine.models.bindingModels;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class EditMedicineBidingModel {

    private Long id;

    private String code;

    @NotEmpty(message = "Въведете наименование")
    private String name;

    @NotEmpty(message = "Въведете мерна единица")
    private String measurement;

    @Size(min = 1, message = "Въведете поне една доза")
    private Integer[] dozes;

    public EditMedicineBidingModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setDozes(Integer[] dozes) {
        this.dozes = dozes;
    }
}
