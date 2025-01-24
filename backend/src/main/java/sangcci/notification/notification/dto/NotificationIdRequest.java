package sangcci.notification.notification.dto;

import lombok.Builder;

@Builder
public record NotificationIdRequest(
        Long memberId,
        Long id
) {

}
