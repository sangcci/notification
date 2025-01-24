package sangcci.notification.notification.infra;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sangcci.notification.notification.fixture.NotificationFixture;
import sangcci.notification.notification.service.NotificationRepository;
import sangcci.notification.support.RepositoryTestSupport;

class NotificationRepositoryTest extends RepositoryTestSupport {

    @Autowired
    protected NotificationRepository notificationRepository;

    @Test
    void notification_bulk_insert_test() {
        var recipientIds = List.of(1L, 2L, 3L);
        var notifications = NotificationFixture.createNotifications(recipientIds);

        notificationRepository.saveAll(notifications);

        var savedNotifications = notificationRepository.findAll();
        assertThat(savedNotifications).hasSize(notifications.size());
    }
}
