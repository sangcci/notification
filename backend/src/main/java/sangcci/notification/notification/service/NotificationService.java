package sangcci.notification.notification.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sangcci.notification.common.event.NotificationEvent;
import sangcci.notification.notification.domain.Notification;
import sangcci.notification.notification.dto.DeviceTokenRequest;
import sangcci.notification.notification.dto.NotificationIdRequest;
import sangcci.notification.notification.infra.DeviceTokenRepository;
import sangcci.notification.notification.infra.NotificationSender;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationSender notificationSender;
    private final NotificationRepository notificationRepository;
    private final DeviceTokenRepository deviceTokenRepository;

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

    public void registerDeviceToken(DeviceTokenRequest deviceTokenRequest) {
        deviceTokenRepository.save(deviceTokenRequest.memberId(), deviceTokenRequest.deviceToken());
    }

    public void deleteDeviceToken(Long memberId) {
        deviceTokenRepository.delete(memberId);
    }

    @Async("notificationTaskExecutor")
    @EventListener
    public void sendMessage(NotificationEvent event) {
        // device token 가져오기 (지금은 fcm 고정)
        List<String> deviceTokens = deviceTokenRepository.findTokensByMemberIds(event.recipientIds());
        if (deviceTokens == null || deviceTokens.isEmpty()) {
            log.debug("알림 전송할 대상이 없습니다.");
            return;
        }
        // 알림 메세지 보내기
        deviceTokens.forEach(fcmToken ->
                notificationSender.sendMessage(fcmToken, event.title(), event.message())
        );
        log.debug("알림 메세지 전송 완료");
        // 알림 메세지 저장
        List<Notification> notificationEntities = event.recipientIds().stream()
                .map(recipientId -> Notification.from(event.title(), event.message(), recipientId))
                .toList();
        notificationRepository.saveAll(notificationEntities);
        log.debug("알림 메세지 저장 완료");
    }
}