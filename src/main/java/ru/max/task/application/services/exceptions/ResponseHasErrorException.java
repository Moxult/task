package ru.max.task.application.services.exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ResponseHasErrorException extends RuntimeException {

    static String MESSAGE_TEMPLATE = "Response contains error %s";

    String error;

    public ResponseHasErrorException(@NonNull String error) {

        super(String.format(MESSAGE_TEMPLATE, error));

        this.error = error;
    }
}
