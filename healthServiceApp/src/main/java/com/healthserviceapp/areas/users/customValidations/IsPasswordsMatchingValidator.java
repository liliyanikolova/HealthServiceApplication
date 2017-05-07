package com.healthserviceapp.areas.users.customValidations;


import com.healthserviceapp.areas.users.models.bindingModels.RegisterDoctorBindingModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordsMatchingValidator implements ConstraintValidator<IsPasswordsMatching, Object> {
    @Override
    public void initialize(IsPasswordsMatching isPasswordsMatching) {

    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {
        if(userClass instanceof RegisterDoctorBindingModel){
            return ((RegisterDoctorBindingModel) userClass).getPassword().equals(((RegisterDoctorBindingModel) userClass).getConfirmPassword());
        }

        return false;
    }
}
