package ru.max.task.application.services;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ru.max.task.application.domain.CustomerEntity;
import ru.max.task.application.messaging.MessagingService;
import ru.max.task.application.messaging.request.SendEmailRequest;

@Service
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RegistrationEmailServiceImpl implements RegistrationEmailService {

    static String TEMPLATE = "success_registration_email";
    static String SUBJECT = "Информация о регистрации";

    @NonNull TemplateEngine templateEngine;
    @NonNull MessagingService messagingService;

    @Override
    public void sendRegistrationEmail(@NonNull CustomerEntity customer) {

        var requestSendEmail = SendEmailRequest.builder()
                .recipient(customer.getEmail())
                .body(buildEmailBody(customer.getName()))
                .subject(SUBJECT)
                .build();

        messagingService.send(requestSendEmail);
    }

    public String buildEmailBody(@NonNull String customerName) {

        var context = new Context();
        context.setVariable("customerName", customerName);

        return templateEngine.process(TEMPLATE, context);
    }
}
