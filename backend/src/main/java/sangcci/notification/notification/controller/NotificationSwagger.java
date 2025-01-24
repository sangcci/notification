package sangcci.notification.notification.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import sangcci.notification.notification.domain.Notification;
import sangcci.notification.notification.dto.NotificationIdRequest;

@Tag(name = "알림 조회 API")
public interface NotificationSwagger {

    @Operation(summary = "사용자 알림 조회")
    ResponseEntity<List<Notification>> getNotifications(
            Long memberId
    );

    @Operation(summary = "사용자 알림 읽음 표시")
    ResponseEntity<Void> markAsRead(
            NotificationIdRequest notificationIdRequest
    );
}
