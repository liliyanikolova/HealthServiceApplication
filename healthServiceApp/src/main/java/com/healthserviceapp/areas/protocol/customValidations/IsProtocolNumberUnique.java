package com.healthserviceapp.areas.protocol.customValidations;

import com.healthserviceapp.areas.common.utils.Messages;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = IsProtocolNumberUniqueValidator.class)
public @interface IsProtocolNumberUnique {

    String message() default Messages.PROTOCOL_CODE_EXIST;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
