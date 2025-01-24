package sangcci.notification.external.fcm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sangcci.notification.external.fcm.dto.FcmMessage;
import sangcci.notification.external.fcm.infra.FcmClient;
import sangcci.notification.external.fcm.util.FcmTokenUtils;
import sangcci.notification.notification.service.NotificationSender;

@Slf4j
@Component
@RequiredArgsConstructor
public class FcmSender implements NotificationSender {

    private final FcmClient fcmClient;

    @Value("${fcm.project-id}")
    private String projectId;

    public void sendMessage(
            final String deviceToken,
            final String title,
            final String message
    ) {
        String accessToken = FcmTokenUtils.getAccessToken();
        String authorizationHeader = "Bearer " + accessToken;
        FcmMessage fcmMessage = FcmMessage.from(deviceToken, false, title, message);
        fcmClient.sendMessage(
                authorizationHeader,
                fcmMessage,
                projectId
        );
        log.info("FCM 메시지 전송 성공");
    }
}
