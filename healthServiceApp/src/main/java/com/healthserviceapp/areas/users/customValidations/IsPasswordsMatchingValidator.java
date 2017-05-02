package com.healthserviceapp.areas.users.customValidations;


import com.healthserviceapp.areas.users.models.bindingModels.RegisterDoctorBidingModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordsMatchingValidator implements ConstraintValidator<IsPasswordsMatching, Object> {
    @Override
    public void initialize(IsPasswordsMatching isPasswordsMatching) {

    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {
        if(userClass instanceof RegisterDoctorBidingModel){
            return ((RegisterDoctorBidingModel) userClass).getPassword().equals(((RegisterDoctorBidingModel) userClass).getConfirmPassword());
        }

        return false;
    }
}
