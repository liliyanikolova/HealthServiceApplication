package com.healthserviceapp.areas.users.models.bindingModels;

import com.healthserviceapp.areas.users.customValidations.IsPasswordsMatching;
import com.healthserviceapp.areas.users.customValidations.IsEmailUnique;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;

@IsPasswordsMatching
public class RegisterDoctorBindingModel {

    @IsEmailUnique
    @NotEmpty(message = "Въведете имейл адрес")
    @Email(message = "Невалиден имейл адрес")
    private String email;

    @NotEmpty(message = "Въведете парола")
    @Size(min = 5, message = "Твърде кратка парола")
    private String password;

    @NotEmpty(message = "Потвърдете паролата")
    private String confirmPassword;

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

    public RegisterDoctorBindingModel() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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
