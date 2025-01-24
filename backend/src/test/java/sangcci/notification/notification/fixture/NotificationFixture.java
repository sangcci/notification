package sangcci.notification.notification.fixture;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sangcci.notification.notification.domain.Notification;
import sangcci.notification.common.event.NotificationEvent;
import sangcci.notification.common.event.NotificationMessage;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NotificationFixture {

    // entity
    public static Notification createNotification(Long recipientId) {
        return Notification.builder()
                .title("test title")
                .message("test content")
                .recipientId(recipientId)
                .build();
    }

    public static List<Notification> createNotifications(List<Long> recipientIds) {
        return recipientIds.stream()
                .map(NotificationFixture::createNotification)
                .toList();
    }

    // event
    public static NotificationEvent createNotificationEvent(List<Long> recipientIds) {
        return NotificationMessage.NEW_NOTIFICATION.toEvent(recipientIds);
    }
}
