package com.tolgaozgun.meettime.annotations;


import com.tolgaozgun.meettime.entity.enums.UserRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiredRole {
    UserRole[] value() default { UserRole.REGISTERED_USER};
}
