package ch.bytecrowd.writesonicclient;

import ch.bytecrowd.writesonicclient.conf.ApplicationShutdownTestConfiguration;
import ch.bytecrowd.writesonicclient.conf.ConsoleReaderTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@Import({ConsoleReaderTestConfiguration.class, ApplicationShutdownTestConfiguration.class})
class WriteSonicClientApplicationIT {

	@SuppressWarnings("EmptyMethod")
	@Test
	void contextLoads() {
	}
}
