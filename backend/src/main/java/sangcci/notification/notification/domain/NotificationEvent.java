package sangcci.notification.notification.domain;

import java.util.List;
import lombok.Builder;
import sangcci.notification.notification.dto.NotificationMessage;

@Builder
public record NotificationEvent(
        List<Long> recipientIds,
        NotificationMessage notificationMessage
) {

}
