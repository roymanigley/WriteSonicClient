package ch.bytecrowd.writesonicclient.conf;

import io.netty.util.internal.StringUtil;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
public class ConsoleReaderTestConfiguration {

    @Bean
    @Primary
    Scanner createConsoleReaderMock() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine()).thenReturn(StringUtil.EMPTY_STRING);
        return scanner;
    }
}
