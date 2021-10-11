package ru.max.task.application.api;

import lombok.NonNull;
import ru.max.task.application.web.dto.RegistrationInput;

public interface RegistrationApi {

    void registerNewCustomer(@NonNull RegistrationInput input);
}
