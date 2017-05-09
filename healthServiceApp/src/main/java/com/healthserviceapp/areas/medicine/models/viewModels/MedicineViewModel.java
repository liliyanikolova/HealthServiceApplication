package com.healthserviceapp.areas.medicine.models.viewModels;

public class MedicineViewModel {

    private Long id;

    private String code;

    private String name;

    private String measurement;

    private Integer[] dozes;

    public MedicineViewModel() {
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
