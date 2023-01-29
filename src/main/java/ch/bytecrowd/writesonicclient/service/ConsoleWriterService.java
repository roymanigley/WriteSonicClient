package ch.bytecrowd.writesonicclient.service;

import org.springframework.stereotype.Service;

@Service
public class ConsoleWriterService {

    public void println(Object object) {
        System.out.println(object);
    }

    public void print(Object object) {
        System.out.print(object);
    }
}
