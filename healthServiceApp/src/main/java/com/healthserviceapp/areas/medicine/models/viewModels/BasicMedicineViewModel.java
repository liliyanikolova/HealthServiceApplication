package com.healthserviceapp.areas.medicine.models.viewModels;

public class BasicMedicineViewModel {

    private Long id;

    private String code;

    private String name;

    public BasicMedicineViewModel() {
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
}
