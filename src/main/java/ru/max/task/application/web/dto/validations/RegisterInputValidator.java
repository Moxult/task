package ru.max.task.application.web.dto.validations;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import ru.max.task.adapters.repositories.CustomerRepository;
import ru.max.task.application.web.dto.RegistrationInput;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RegisterInputValidator implements ConstraintValidator<RegisterInputConstraint, RegistrationInput> {

    static  String LOGIN_OR_EMAIL_ALREADY_EXISTS_ERROR_CODE = "Пользователь с такими данными уже существует";

    @NonNull CustomerRepository customerRepository;

    @Override
    public void initialize(RegisterInputConstraint constraintAnnotation) {
        //empty
    }

    @Override
    public boolean isValid(@NonNull final RegistrationInput input, final ConstraintValidatorContext context) {

        context.disableDefaultConstraintViolation();

        var existsEmailOrLogin = customerRepository.existsByEmailOrLogin(input.getEmail(), input.getLogin());
        if (existsEmailOrLogin) {
            context.buildConstraintViolationWithTemplate(LOGIN_OR_EMAIL_ALREADY_EXISTS_ERROR_CODE)
                    .addConstraintViolation();

            return false;
        }

        return true;
    }
}