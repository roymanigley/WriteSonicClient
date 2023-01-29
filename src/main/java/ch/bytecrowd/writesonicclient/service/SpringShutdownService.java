package ch.bytecrowd.writesonicclient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpringShutdownService {

    final private ApplicationContext context;

    public void shutdown() {
        SpringApplication.exit(context);
    }
}
