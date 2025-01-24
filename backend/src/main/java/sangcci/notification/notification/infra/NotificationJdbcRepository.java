package sangcci.notification.notification.infra;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sangcci.notification.notification.domain.Notification;

@Repository
@RequiredArgsConstructor
public class NotificationJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveAllInBatch(List<Notification> notifications) {
        jdbcTemplate.batchUpdate("INSERT INTO notifications (title, message, recipient_id, is_read) VALUES (?, ?, ?, false)",
                notifications,
                notifications.size(),
                (ps, notification) -> {
                    ps.setString(1, notification.getTitle());
                    ps.setString(2, notification.getMessage());
                    ps.setLong(3, notification.getRecipientId());
                });
    }
}
