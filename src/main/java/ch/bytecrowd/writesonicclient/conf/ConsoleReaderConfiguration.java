package ch.bytecrowd.writesonicclient.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class ConsoleReaderConfiguration {

    @Bean
    Scanner createConsoleReader() {
        return new Scanner(System.in);
    }
}
