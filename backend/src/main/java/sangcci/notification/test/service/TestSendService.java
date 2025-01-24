package sangcci.notification.test.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import sangcci.notification.notification.domain.NotificationEvent;
import sangcci.notification.notification.dto.NotificationMessage;

@Service
@RequiredArgsConstructor
public class TestSendService {

    private final ApplicationEventPublisher publisher;

    public void sendTestMessage(final Long memberId) {
        // main business logic...

        // notification event publish
        NotificationEvent notificationEvent = NotificationEvent.builder()
                .recipientIds(List.of(memberId))
                .notificationMessage(NotificationMessage.NEW_NOTIFICATION)
                .build();
        publisher.publishEvent(notificationEvent);
    }
}
