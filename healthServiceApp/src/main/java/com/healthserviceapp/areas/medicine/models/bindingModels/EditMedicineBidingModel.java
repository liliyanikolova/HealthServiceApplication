package com.healthserviceapp.areas.medicine.models.bindingModels;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.List;

public class EditMedicineBidingModel {

    private Long id;

    private String code;

    @NotEmpty(message = "Въведете наименование")
    private String name;

    @NotEmpty(message = "Въведете мерна единица")
    private String measurement;

    @Size(min = 1, message = "Въведете поне една доза")
    private List<Integer> dozes;

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

    public List<Integer> getDozes() {
        return dozes;
    }

    public void setDozes(List<Integer> dozes) {
        this.dozes = dozes;
    }
}
