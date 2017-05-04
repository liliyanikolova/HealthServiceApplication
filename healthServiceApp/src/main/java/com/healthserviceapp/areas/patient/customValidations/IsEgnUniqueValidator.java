package com.healthserviceapp.areas.patient.customValidations;

import com.healthserviceapp.areas.patient.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsEgnUniqueValidator implements ConstraintValidator<IsEgnUnique, Object> {

    private PatientService patientService;

    @Autowired
    public IsEgnUniqueValidator(PatientService patientService) {
        this.patientService = patientService;
    }

    public void initialize(IsEgnUnique constraint) {
    }

    public boolean isValid(Object egn, ConstraintValidatorContext context) {

        if (egn instanceof String) {
            return !this.patientService.doesEgnExist(((String) egn));
        }

        return true;
    }
}
