package sangcci.notification.notification.infra;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import sangcci.notification.notification.domain.Notification;

public interface NotificationJpaRepository extends JpaRepository<Notification, Long> {
    List<Notification> findNotificationsByRecipientId(Long recipientId);
}
