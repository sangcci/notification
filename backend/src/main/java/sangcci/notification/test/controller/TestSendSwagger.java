package sangcci.notification.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;
import sangcci.notification.notification.domain.Notification;

@Tag(name = "알림 테스트용 API")
public interface TestSendSwagger {

    @Operation(summary = "테스트용 알림 보내기")
    ResponseEntity<List<Notification>> sendNotificationsForTest(
            Long memberId
    );
}