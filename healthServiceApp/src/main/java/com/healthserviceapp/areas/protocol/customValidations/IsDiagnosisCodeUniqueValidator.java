package com.healthserviceapp.areas.protocol.customValidations;

import com.healthserviceapp.areas.patient.services.PatientService;
import com.healthserviceapp.areas.protocol.services.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsDiagnosisCodeUniqueValidator implements ConstraintValidator<IsDiagnosisCodeUnique, Object> {

    private DiagnosisService diagnosisService;

    @Autowired
    public IsDiagnosisCodeUniqueValidator(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    public void initialize(IsDiagnosisCodeUnique constraint) {
    }

    public boolean isValid(Object code, ConstraintValidatorContext context) {

        if (code instanceof String) {
            return !this.diagnosisService.doesCodeExist(((String) code));
        }

        return true;
    }
}
