package ru.max.task.adapters.repositories;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.max.task.application.domain.CustomerEntity;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByLogin(@NonNull String login);

    boolean existsByEmailOrLogin(@NonNull String email, @NonNull String login);

    default CustomerEntity findByLoginOrThrow(@NonNull String login) {
        return findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(login));
    }
}
