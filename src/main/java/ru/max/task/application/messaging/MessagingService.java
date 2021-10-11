package ru.max.task.application.messaging;
import lombok.NonNull;
import ru.max.task.application.messaging.request.CommonRequest;
import ru.max.task.application.messaging.response.CommonResponse;

public interface MessagingService {

    CommonResponse sendAndReceive(@NonNull CommonRequest request);

    void send(@NonNull CommonRequest request);
}
