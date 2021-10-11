package ru.max.task.application.messaging.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SendEmailRequest implements CommonRequest {

    static String ROUTING_KEY = "task.routing.event.external.system";

    String recipient;

    String subject;

    String body;

    @Override
    public String getRoutingKey() {
        return ROUTING_KEY;
    }
}
