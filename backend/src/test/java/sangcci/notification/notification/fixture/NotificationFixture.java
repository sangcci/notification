package sangcci.notification.notification.fixture;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sangcci.notification.notification.domain.Notification;
import sangcci.notification.notification.domain.NotificationEvent;
import sangcci.notification.notification.dto.NotificationMessage;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NotificationFixture {

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

    public static NotificationEvent createNotificationEvent(List<Long> recipientIds) {
        return NotificationEvent.builder()
                .recipientIds(recipientIds)
                .notificationMessage(NotificationMessage.NEW_NOTIFICATION)
                .build();
    }
}
