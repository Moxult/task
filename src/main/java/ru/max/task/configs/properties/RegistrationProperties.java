package ru.max.task.configs.properties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.Duration;

@Validated
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "task.registration")
public class RegistrationProperties {

    @Valid
    @NotNull ExternalSystem externalSystem = new ExternalSystem();

    @Validated
    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ExternalSystem {

        @NotEmpty
        String exchange = "task.exchange";

        @NotNull
        Duration confirmTimeout = Duration.ofSeconds(5);
    }
}
