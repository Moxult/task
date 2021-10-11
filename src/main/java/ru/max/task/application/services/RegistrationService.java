package ru.max.task.application.services;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.max.task.adapters.repositories.CustomerRepository;
import ru.max.task.application.api.RegistrationApi;
import ru.max.task.application.domain.CustomerEntity;
import ru.max.task.application.messaging.MessagingService;
import ru.max.task.application.messaging.request.RegistrationRequest;
import ru.max.task.application.web.dto.RegistrationInput;

import javax.transaction.Transactional;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RegistrationService implements RegistrationApi {

    @NonNull CustomerRepository customerRepository;
    @NonNull PasswordEncoder passwordEncoder;
    @NonNull MessagingService messagingService;
    @NonNull RegistrationEmailService registrationEmailService;

    @Transactional
    @Override
    public void registerNewCustomer(@NonNull RegistrationInput input) {

        var encodedPassword = passwordEncoder.encode(input.getPassword());

        var customer = CustomerEntity.builder()
                .email(input.getEmail())
                .login(input.getLogin())
                .password(encodedPassword)
                .name(input.getName())
                .build();

        saveCustomer(customer);

        sendRegistration(customer);

        registrationEmailService.sendRegistrationEmail(customer);
    }

    private void saveCustomer(CustomerEntity customerEntity) {

        customerRepository.save(customerEntity);
    }

    private void sendRegistration(CustomerEntity customerEntity) {

        var request = RegistrationRequest.builder()
                .login(customerEntity.getLogin())
                .email(customerEntity.getEmail())
                .name(customerEntity.getName())
                .build();
        var response = messagingService.sendAndReceive(request);

        log.debug("Get Response: {}",response);
    }
}
