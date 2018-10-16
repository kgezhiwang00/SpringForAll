//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jr.basic.meta.domain.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

@Pattern(
    regexp = "^(\\d{15}|\\d{18})$"
)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
    validatedBy = {}
)
@Documented
public @interface IdCard {
    String message() default "{org.hibernate.validator.constraints.IdCard.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
