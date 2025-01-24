package sangcci.notification.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");

        return new OpenAPI()
                .info(new Info().title("Notification"))
                .servers(List.of(server));
    }
}
