package sangcci.notification.notification.service;

import java.util.List;
import java.util.Optional;
import sangcci.notification.notification.domain.Notification;

public interface NotificationRepository {
    Optional<Notification> findById(Long id);
    List<Notification> findNotificationsByRecipientId(Long recipientId);
    List<Notification> findAll();

    void saveAll(List<Notification> notifications);
}
