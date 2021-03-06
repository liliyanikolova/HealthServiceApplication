package com.healthserviceapp.areas.users.customValidations;

import com.healthserviceapp.areas.common.utils.Messages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = IsEmailUniqueValidator.class)
public @interface IsEmailUnique {

    String message() default Messages.EMAIL_EXIST;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
