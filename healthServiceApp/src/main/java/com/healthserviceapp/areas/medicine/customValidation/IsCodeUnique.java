package com.healthserviceapp.areas.medicine.customValidation;

import com.healthserviceapp.areas.common.utils.Messages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = IsCodeUniqueValidator.class)
public @interface IsCodeUnique {

    String message() default Messages.MEDICINE_CODE_EXIST;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
