package com.healthserviceapp.areas.patient.customValidations;

import com.healthserviceapp.areas.common.utils.Messages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = IsEgnUniqueValidator.class)
public @interface IsEgnUnique {

    String message() default Messages.EGN_EXIST;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
