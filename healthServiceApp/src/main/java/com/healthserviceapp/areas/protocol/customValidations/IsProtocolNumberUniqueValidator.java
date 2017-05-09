package com.healthserviceapp.areas.protocol.customValidations;

import com.healthserviceapp.areas.protocol.services.ProtocolService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsProtocolNumberUniqueValidator implements ConstraintValidator<IsProtocolNumberUnique, Object> {

    private ProtocolService protocolService;

    @Autowired
    public IsProtocolNumberUniqueValidator(ProtocolService protocolService) {
        this.protocolService = protocolService;
    }

    public void initialize(IsProtocolNumberUnique constraint) {
    }

    public boolean isValid(Object code, ConstraintValidatorContext context) {

        if (code instanceof String) {
            return !this.protocolService.doesNumberExist(((String) code));
        }

        return true;
    }
}
