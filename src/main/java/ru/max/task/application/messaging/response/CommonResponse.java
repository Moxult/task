package ru.max.task.application.messaging.response;

import java.io.Serializable;

public interface CommonResponse extends Serializable {

    boolean hasError();

    String getErrorDescription();
}
