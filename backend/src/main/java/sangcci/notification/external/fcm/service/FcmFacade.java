package sangcci.notification.external.fcm.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sangcci.notification.notification.dto.NotificationMessage;
import sangcci.notification.notification.infra.NotificationSender;

@Slf4j
@Service
@RequiredArgsConstructor
public class FcmFacade implements NotificationSender {

    private final FcmSender fcmSender;
    private final DeviceTokenService deviceTokenService;

    @Override
    public void sendMessage(
            final List<Long> recipientIds,
            final NotificationMessage notificationMessage
    ) {
        // firebase device token 가져오기
        List<String> recipientFcmTokens = deviceTokenService.getDeviceTokens(recipientIds);
        if (recipientFcmTokens == null || recipientFcmTokens.isEmpty()) {
            log.info("알림 전송할 대상이 없습니다.");
            return;
        }
        // fcm에 각각 알림 메세지 보내기
        recipientFcmTokens.forEach(fcmToken ->
                fcmSender.sendMessage(fcmToken, notificationMessage)
        );
    }
}
