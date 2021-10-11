package ru.max.task.application.web.dto.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = RegisterInputValidator.class)
public @interface RegisterInputConstraint {
    String message() default "{Ошибка при регистрации}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}