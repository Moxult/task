package ru.max.task.configs;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.max.task.configs.properties.RegistrationProperties;

@Log4j2
@Configuration
@EnableConfigurationProperties(RegistrationProperties.class)
@RequiredArgsConstructor
public class AmqpConfig {

    @Bean
    public TopicExchange exchange(@NonNull RegistrationProperties registrationProperties) {
        return new TopicExchange(registrationProperties.getExternalSystem().getExchange());
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate amqpTemplate(@NonNull ConnectionFactory connectionFactory,
                                       @NonNull RegistrationProperties registrationProperties) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        rabbitTemplate.setUseTemporaryReplyQueues(true);
        rabbitTemplate.setReplyTimeout(registrationProperties
                .getExternalSystem().getConfirmTimeout().toMillis());
        return rabbitTemplate;
    }
}
