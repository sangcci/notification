package sangcci.notification.external.fcm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import sangcci.notification.notification.dto.NotificationMessage;

@Builder
public record FcmMessage(
        @JsonProperty("validate_only") boolean validateOnly,
        Message message
) {
    @Builder
    public record Message(
            Notification notification,
            String token
    ) {

    }

    @Builder
    public record Notification(
            String title,
            String body
    ) {

    }

    public static FcmMessage from(
            final String token,
            final boolean validateOnly,
            final NotificationMessage notificationMessage
    ) {
        return FcmMessage.builder()
                .validateOnly(validateOnly)
                .message(Message.builder()
                        .notification(Notification.builder()
                                .title(notificationMessage.getTitle())
                                .body(notificationMessage.getMessage())
                                .build())
                        .token(token)
                        .build())
                .build();
    }
}
