package sangcci.notification.external.fcm.infra;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeviceTokenRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public List<String> findTokensByMemberIds(List<Long> memberIds) {
        List<String> memberIdKeys = memberIds.stream()
                .map(this::buildRedisKey)
                .toList();
        return redisTemplate.opsForValue().multiGet(memberIdKeys);
    }

    public void save(Long memberId, String deviceToken) {
        String redisKey = buildRedisKey(memberId);
        redisTemplate.opsForValue().set(redisKey, deviceToken);
    }

    public void delete(Long memberId) {
        String redisKey = buildRedisKey(memberId);
        redisTemplate.delete(redisKey);
    }

    private String buildRedisKey(final Long memberId) {
        return String.format("fcmDeviceToken:%d", memberId);
    }
}
