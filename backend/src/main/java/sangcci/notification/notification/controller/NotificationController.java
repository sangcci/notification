package sangcci.notification.notification.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sangcci.notification.notification.domain.Notification;
import sangcci.notification.notification.dto.NotificationIdRequest;
import sangcci.notification.notification.service.NotificationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController implements NotificationSwagger {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications(
            @RequestParam final Long memberId
    ) {
        return ResponseEntity.ok(notificationService.getNotificationsByRecipientId(memberId));
    }

    @PatchMapping("/read")
    public ResponseEntity<Void> markAsRead(
            @RequestBody final NotificationIdRequest notificationIdRequest
    ) {
        notificationService.markAsRead(notificationIdRequest);
        return ResponseEntity.noContent().build();
    }
}
