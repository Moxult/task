package ru.max.task.application.services;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.max.task.application.security.CustomerDetail;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService implements UserDetailsService {

    public CustomerService(@NonNull @Qualifier("datasourceCustomerDetail") CustomerDetail applicationCustomerDetail) {
        this.applicationCustomerDetail = applicationCustomerDetail;
    }

    @NonNull CustomerDetail applicationCustomerDetail;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return applicationCustomerDetail.getCustomerByLogin(login);
    }
}
