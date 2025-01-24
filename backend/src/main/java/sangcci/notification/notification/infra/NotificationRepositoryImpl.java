package sangcci.notification.notification.infra;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import sangcci.notification.notification.domain.Notification;
import sangcci.notification.notification.service.NotificationRepository;

@Repository
@RequiredArgsConstructor
public class NotificationRepositoryImpl implements NotificationRepository {

    private final NotificationJpaRepository notificationJpaRepository;
    private final NotificationJdbcRepository notificationJdbcRepository;

    @Override
    public Optional<Notification> findById(final Long id) {
        return notificationJpaRepository.findById(id);
    }

    @Override
    public List<Notification> findNotificationsByRecipientId(final Long recipientId) {
        return notificationJpaRepository.findNotificationsByRecipientId(recipientId);
    }

    @Override
    public List<Notification> findAll() {
        return notificationJpaRepository.findAll();
    }

    @Override
    public void saveAll(final List<Notification> notifications) {
        notificationJdbcRepository.saveAllInBatch(notifications);
    }
}
