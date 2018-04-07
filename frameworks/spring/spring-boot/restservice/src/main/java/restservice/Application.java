package restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* @Configuration : configuration class
* @ComponentScan : scans for component classes
* @EnableAutoConfiguration : enables Spring Boot to create beans based on classpath classes
*/

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
