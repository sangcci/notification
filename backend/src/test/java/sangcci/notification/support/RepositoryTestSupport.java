package sangcci.notification.support;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import sangcci.notification.notification.infra.NotificationJdbcRepository;
import sangcci.notification.notification.infra.NotificationRepositoryImpl;

@DataJpaTest
@Import({NotificationRepositoryImpl.class, NotificationJdbcRepository.class})
//@AutoConfigureTestDatabase(replace= Replace.NONE)
public abstract class RepositoryTestSupport {

}
