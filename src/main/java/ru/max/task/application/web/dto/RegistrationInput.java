package ru.max.task.application.web.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.max.task.application.web.dto.validations.RegisterInputConstraint;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RegisterInputConstraint
public class RegistrationInput {

    @NotEmpty
    @Size(min = 3, max = 45)
    String login;

    @NotEmpty
    @Size(min = 6, max = 64)
    String password;

    @NotEmpty
    @Email
    @Size(max = 45)
    String email;

    @NotEmpty
    @Size(max = 20)
    String name;
}
