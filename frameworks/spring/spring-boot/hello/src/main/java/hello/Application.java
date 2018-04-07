package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// automatically enables component scanning, as opposed to Spring's explicit component scanning and factory fetching
// combines @Configuration, @EnableAutoConfiguration (classpath based bean creation and wiring) and @ComponentScan
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
