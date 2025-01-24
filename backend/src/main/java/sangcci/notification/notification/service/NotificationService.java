package sangcci.notification.notification.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangcci.notification.notification.domain.Notification;
import sangcci.notification.notification.domain.NotificationEvent;
import sangcci.notification.notification.dto.NotificationIdRequest;
import sangcci.notification.notification.dto.NotificationMessage;
import sangcci.notification.notification.infra.NotificationSender;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationSender notificationSender;
    private final NotificationRepository notificationRepository;

    @Transactional(readOnly = true)
    public List<Notification> getNotificationsByRecipientId(Long recipientId) {
        return notificationRepository.findNotificationsByRecipientId(recipientId);
    }

    @Transactional
    public void markAsRead(final NotificationIdRequest notificationIdRequest) {
        Notification notification = notificationRepository.findById(notificationIdRequest.id())
                .orElseThrow(IllegalArgumentException::new);
        notification.validateMine(notificationIdRequest.memberId());
        notification.read();
    }

    @Async("notificationTaskExecutor")
    @EventListener
    public void sendMessage(NotificationEvent event) {
        notificationSender.sendMessage(
                event.recipientIds(),
                event.notificationMessage()
        );

        List<Notification> notificationEntities = event.recipientIds().stream()
                .map(recipientId -> createNotification(recipientId, event.notificationMessage()))
                .toList();
        notificationRepository.saveAll(notificationEntities);
    }

    private Notification createNotification(Long recipientId, NotificationMessage notificationMessage) {
        return Notification.builder()
                .recipientId(recipientId)
                .title(notificationMessage.getTitle())
                .message(notificationMessage.getMessage())
                .build();
    }
}