package ru.max.task.application.messaging.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationResponse implements CommonResponse {

    final static String SUCCESS_ANSWER = "Ok";

    String state;

    String errorDescription;

    @Override
    public boolean hasError() {
        return !SUCCESS_ANSWER.equals(state) && errorDescription != null;
    }
}
