package sangcci.notification.external.fcm.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sangcci.notification.external.fcm.infra.DeviceTokenRepository;
import sangcci.notification.external.fcm.dto.DeviceTokenRequest;

@Service
@RequiredArgsConstructor
public class DeviceTokenService {

    private final DeviceTokenRepository deviceTokenRepository;

    public List<String> getDeviceTokens(List<Long> memberIds) {
        return deviceTokenRepository.findTokensByMemberIds(memberIds);
    }

    public void registerDeviceToken(DeviceTokenRequest deviceTokenRequest) {
        deviceTokenRepository.save(deviceTokenRequest.memberId(), deviceTokenRequest.deviceToken());
    }

    public void deleteDeviceToken(Long memberId) {
        deviceTokenRepository.delete(memberId);
    }
}
