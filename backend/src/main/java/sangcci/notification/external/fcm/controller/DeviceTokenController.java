package sangcci.notification.external.fcm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sangcci.notification.external.fcm.service.DeviceTokenService;
import sangcci.notification.external.fcm.dto.DeviceTokenRequest;

@RestController
@RequestMapping("/api/v1/firebase/device-token")
@RequiredArgsConstructor
public class DeviceTokenController implements DeviceTokenSwagger {

    private final DeviceTokenService deviceTokenService;

    @PostMapping
    public ResponseEntity<Void> registerDeviceToken(
            @RequestBody final DeviceTokenRequest deviceTokenRequest
    ) {
        deviceTokenService.registerDeviceToken(deviceTokenRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDeviceToken(
            @RequestBody final Long memberId
    ) {
        deviceTokenService.deleteDeviceToken(memberId);
        return ResponseEntity.noContent().build();
    }
}
