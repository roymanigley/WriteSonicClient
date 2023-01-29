package ch.bytecrowd.writesonicclient.conf;

import ch.bytecrowd.writesonicclient.service.SpringShutdownService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class ApplicationShutdownTestConfiguration {

    @Bean
    @Primary
    SpringShutdownService createApplicationShutdownServiceMock() {
        SpringShutdownService scanner = mock(SpringShutdownService.class);
        return scanner;
    }
}
