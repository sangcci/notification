package sangcci.notification.external.fcm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import sangcci.notification.external.fcm.dto.DeviceTokenRequest;

@Tag(name = "FCM 디바이스 토큰 API")
public interface DeviceTokenSwagger {

    @Operation(summary = "디바이스 토큰 등록")
    ResponseEntity<Void> registerDeviceToken(
            DeviceTokenRequest deviceTokenRequest
    );

    @Operation(summary = "디바이스 토큰 삭제")
    ResponseEntity<Void> deleteDeviceToken(
            Long memberId
    );
}
