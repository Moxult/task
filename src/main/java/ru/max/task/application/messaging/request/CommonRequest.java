package ru.max.task.application.messaging.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface CommonRequest {

    @JsonIgnore
    String getRoutingKey();
}
