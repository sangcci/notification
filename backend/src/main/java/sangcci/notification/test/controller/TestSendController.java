package sangcci.notification.test.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sangcci.notification.notification.domain.Notification;
import sangcci.notification.notification.service.NotificationService;
import sangcci.notification.test.service.TestSendService;

@RestController
@RequiredArgsConstructor
public class TestSendController implements TestSendSwagger{

    public final TestSendService testSendService;
    public final NotificationService notificationService;

    @GetMapping("/send")
    public ResponseEntity<List<Notification>> sendNotificationsForTest(
            @RequestParam final Long memberId
    ) {
        testSendService.sendTestMessage(memberId);
        return ResponseEntity.ok(notificationService.getNotificationsByRecipientId(memberId));
    }
}
