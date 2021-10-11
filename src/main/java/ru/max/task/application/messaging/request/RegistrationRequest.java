package ru.max.task.application.messaging.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegistrationRequest implements CommonRequest {

    static String ROUTING_KEY = "task.routing.out.external.system";

    String login;

    String email;

    String name;

    @Override
    public String getRoutingKey() {
        return ROUTING_KEY;
    }
}
