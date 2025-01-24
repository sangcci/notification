package sangcci.notification.notification.infra;

public interface NotificationSender {

    void sendMessage(
            String deviceToken,
            String title,
            String message
    );
}
