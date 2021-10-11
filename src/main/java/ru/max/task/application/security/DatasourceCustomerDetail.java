package ru.max.task.application.security;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.max.task.adapters.repositories.CustomerRepository;

@Component
@RequiredArgsConstructor
public class DatasourceCustomerDetail implements CustomerDetail {

    @NonNull CustomerRepository customerRepository;

    @Override
    public ApplicationCustomer getCustomerByLogin(String login) {
        var customerEntity = customerRepository.findByLoginOrThrow(login);

        return ApplicationCustomer.builder()
                .login(customerEntity.getLogin())
                .password(customerEntity.getPassword())
                .build();
    }
}
