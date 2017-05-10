package com.healthserviceapp.areas.medicine.customValidation;

import com.healthserviceapp.areas.medicine.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsCodeUniqueValidator implements ConstraintValidator<IsCodeUnique, Object> {

    private MedicineService medicineService;

    @Autowired
    public IsCodeUniqueValidator(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public void initialize(IsCodeUnique constraint) {
    }

    public boolean isValid(Object code, ConstraintValidatorContext context) {

        return !(code instanceof String) || !this.medicineService.doesCodeExist(((String) code));

    }
}
