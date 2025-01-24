package sangcci.notification.notification.infra;

import java.util.List;
import sangcci.notification.notification.dto.NotificationMessage;

public interface NotificationSender {

    void sendMessage(List<Long> recipientIds, NotificationMessage notificationMessage);
}
