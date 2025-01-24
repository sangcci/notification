package sangcci.notification.notification.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import sangcci.notification.notification.fixture.NotificationFixture;
import sangcci.notification.notification.infra.NotificationSender;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;
    @Mock
    private NotificationRepository notificationRepository;
    @Mock
    private NotificationSender notificationSender;

    @Test
    void send_notification() {
        var recipientIds = List.of(1L, 2L, 3L);
        var notificationEvent = NotificationFixture.createNotificationEvent(recipientIds);

        notificationService.sendMessage(notificationEvent);

        verify(notificationSender, times(1)).sendMessage(any(), any());
        verify(notificationRepository, times(1)).saveAll(any());
    }
}
