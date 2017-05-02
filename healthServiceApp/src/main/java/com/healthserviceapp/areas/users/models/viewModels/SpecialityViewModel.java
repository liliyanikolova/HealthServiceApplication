package com.healthserviceapp.areas.users.models.viewModels;

public class SpecialityViewModel {

    private String code;

    private String name;

    public SpecialityViewModel() {
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }
}
