package com.healthserviceapp.areas.users.models.bindingModels;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class EditDoctorBidingModel {

    private Long id;

    @NotEmpty(message = "Въведете УИН")
    @Size(min = 10, max = 10, message = "Невалиден УИН")
    private String uin;

    @NotEmpty(message = "Изберете титла")
    private String title;

    @NotEmpty(message = "Въведете име")
    private String firstName;

    @NotEmpty(message = "Въведете фамилия")
    private String lastName;

    @NotEmpty(message = "Изперете поне една специалност")
    private String[] specialities;

    private byte[] profileImage;

    public EditDoctorBidingModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String[] getSpecialities() {
        return specialities;
    }

    public void setSpecialities(String[] specialities) {
        this.specialities = specialities;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(byte[] profileImage) {
        this.profileImage = profileImage;
    }
}
