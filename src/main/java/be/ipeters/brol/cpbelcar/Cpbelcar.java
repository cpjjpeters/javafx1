package be.ipeters.brol.cpbelcar;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ch.qos.logback.classic.*;
@SpringBootApplication
public class Cpbelcar {
    private final static Logger log = (Logger) LoggerFactory.getLogger(Cpbelcar.class);
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        System.out.println("start CpbelcarApplication");

        SpringApplication.run(Cpbelcar.class, args);

        System.out.println("STARTED");
    }

}