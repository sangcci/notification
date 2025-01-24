package sangcci.notification.external.fcm.util;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class FcmTokenUtils {

    private static final String KEY_PATH = "src/main/resources/firebase/firebase-service-account.json";
    private static final GoogleCredentials googleCredentials;
    private static AccessToken accessToken;

    static {
        try (FileInputStream serviceAccount = new FileInputStream(KEY_PATH)) {
            googleCredentials = GoogleCredentials
                    .fromStream(serviceAccount)
                    .createScoped(List.of("https://www.googleapis.com/auth/firebase.messaging"));
        } catch (IOException e) {
            log.error("GoogleCredentials 초기화 실패");
            throw new RuntimeException("GoogleCredentials 초기화 실패", e);
        }
    }

    public static String getAccessToken() {
        try {
            // 기존 캐시된 토큰이 존재하고, 만료되지 않았다면 재사용
            if (accessToken != null && !isTokenExpired(accessToken)) {
                log.info("기존 엑세스 토큰 재사용. 만료 시간: {}", accessToken.getExpirationTime());
                return accessToken.getTokenValue();
            }
            // 토큰 갱신
            googleCredentials.refreshIfExpired();
            accessToken = googleCredentials.getAccessToken();
            log.info("새 엑세스 토큰 발급. 만료 시간: {}", accessToken.getExpirationTime());
            return accessToken.getTokenValue();
        } catch (IOException e) {
            log.error("엑세스 토큰 발급 실패");
            throw new RuntimeException("엑세스 토큰  발급 실패", e);
        }
    }

    private static boolean isTokenExpired(AccessToken token) {
        return token.getExpirationTime().toInstant().isBefore(Instant.now());
    }
}