package sangcci.notification.notification.service;

public interface NotificationSender {

    void sendMessage(
            String deviceToken,
            String title,
            String message
    );
}
