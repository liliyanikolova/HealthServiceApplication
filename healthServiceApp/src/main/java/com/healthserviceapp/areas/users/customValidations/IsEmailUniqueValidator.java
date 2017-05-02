package com.healthserviceapp.areas.users.customValidations;

import com.healthserviceapp.areas.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsEmailUniqueValidator implements ConstraintValidator<IsEmailUnique, Object> {

    private UserService userService;

    @Autowired
    public IsEmailUniqueValidator(UserService userService) {
        this.userService = userService;
    }

    public void initialize(IsEmailUnique constraint) {
    }

    public boolean isValid(Object username, ConstraintValidatorContext context) {

        if (username instanceof String) {
            return !this.userService.doesEmailExist(((String) username));
        }

        return true;
    }
}
