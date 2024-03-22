package com.tolgaozgun.meettime.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Documented
@Constraint(validatedBy = RoomDatesValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRoomDates {
    String message() default "Invalid room dates for the selected room type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
