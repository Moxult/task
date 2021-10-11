package ru.max.task.application.services;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;
import ru.max.task.application.messaging.MessagingService;
import ru.max.task.application.messaging.request.CommonRequest;
import ru.max.task.application.messaging.response.CommonResponse;
import ru.max.task.application.services.exceptions.ExternalSystemTimeOutException;
import ru.max.task.application.services.exceptions.ResponseHasErrorException;
import ru.max.task.configs.properties.RegistrationProperties;

@Log4j2
@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RegistrationSenderService implements MessagingService {

    @NonNull AmqpTemplate rabbitTemplate;
    @NonNull RegistrationProperties registrationProperties;

    @Override
    public CommonResponse sendAndReceive(@NonNull CommonRequest request) {
        var exchange = registrationProperties.getExternalSystem().getExchange();

        var response = (CommonResponse) rabbitTemplate.convertSendAndReceive(exchange, request.getRoutingKey(), request);
        responseHandling(response);

        return response;
    }

    @Override
    public void send(@NonNull CommonRequest request) {
        var exchange = registrationProperties.getExternalSystem().getExchange();
        rabbitTemplate.convertAndSend(exchange,request.getRoutingKey(),request);
    }

    private void responseHandling(CommonResponse response) {

        if (response == null) {
            throw new ExternalSystemTimeOutException();
        }
        else if (response.hasError()) {
            throw new ResponseHasErrorException(response.getErrorDescription());
        }
    }
}
