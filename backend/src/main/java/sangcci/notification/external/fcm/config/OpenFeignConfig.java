package sangcci.notification.external.fcm.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "sangcci.notification.external.fcm.infra")
public class OpenFeignConfig {

}
