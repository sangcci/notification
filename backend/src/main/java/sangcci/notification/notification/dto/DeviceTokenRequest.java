package sangcci.notification.notification.dto;

import lombok.Builder;

@Builder
public record DeviceTokenRequest(
        Long memberId,
        String deviceToken
) {

}
