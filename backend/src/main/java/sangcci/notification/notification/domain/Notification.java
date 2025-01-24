package sangcci.notification.notification.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "notifications")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private Long recipientId;
    private boolean isRead;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    private Notification(String title, String message, Long recipientId) {
        this.title = title;
        this.message = message;
        this.recipientId = recipientId;
        this.isRead = false;
    }

    public static Notification from(String title, String message, Long recipientId) {
        return Notification.builder()
                .title(title)
                .message(message)
                .recipientId(recipientId)
                .build();
    }

    public void validateMine(Long recipientId) {
        if (!this.recipientId.equals(recipientId)) {
            throw new IllegalArgumentException("Not your notification");
        }
    }

    public void read() {
        this.isRead = true;
    }
}
