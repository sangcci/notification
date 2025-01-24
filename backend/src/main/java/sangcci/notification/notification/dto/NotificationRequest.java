package sangcci.notification.notification.dto;

import java.util.List;
import lombok.Builder;

@Builder
public record NotificationRequest(
        List<Long> recipientIds,
        String title,
        String body
) {

}
