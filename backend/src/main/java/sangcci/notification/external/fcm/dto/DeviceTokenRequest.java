package sangcci.notification.external.fcm.dto;

import lombok.Builder;

@Builder
public record DeviceTokenRequest(
        Long memberId,
        String deviceToken
) {

}
