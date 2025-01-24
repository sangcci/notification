package sangcci.notification.notification.dto;

import lombok.Getter;

@Getter
public enum NotificationMessage {

    NEW_NOTIFICATION("새로운 알림 등록", "새로운 알림이 도착했습니다");

    private final String title;
    private final String message;

    NotificationMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }
}
