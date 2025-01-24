package sangcci.notification.notification.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sangcci.notification.common.event.NotificationEvent;
import sangcci.notification.common.event.NotificationMessage;
import sangcci.notification.notification.domain.Notification;
import sangcci.notification.notification.dto.DeviceTokenRequest;
import sangcci.notification.notification.dto.NotificationIdRequest;
import sangcci.notification.notification.service.NotificationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notifications")
public class NotificationController implements NotificationSwagger {

    private final NotificationService notificationService;

    @GetMapping("/send")
    public ResponseEntity<List<Notification>> sendNotificationsForTest(
            @RequestParam final Long memberId
    ) {
        NotificationEvent event = NotificationMessage.NEW_NOTIFICATION.toEvent(List.of(memberId));
        notificationService.sendMessage(event);
        return ResponseEntity.ok(notificationService.getNotificationsByRecipientId(memberId));
    }

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

    @PostMapping("/device-token")
    public ResponseEntity<Void> registerDeviceToken(
            @RequestBody final DeviceTokenRequest deviceTokenRequest
    ) {
        notificationService.registerDeviceToken(deviceTokenRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/device-token")
    public ResponseEntity<Void> deleteDeviceToken(
            @RequestBody final Long memberId
    ) {
        notificationService.deleteDeviceToken(memberId);
        return ResponseEntity.noContent().build();
    }
}
