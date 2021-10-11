package ru.max.task.application.services;

import lombok.NonNull;
import ru.max.task.application.domain.CustomerEntity;

public interface RegistrationEmailService {

    void sendRegistrationEmail(@NonNull CustomerEntity customerEntity);
}
