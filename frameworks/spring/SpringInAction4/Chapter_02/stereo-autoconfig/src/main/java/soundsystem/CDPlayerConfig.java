package soundsystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// explicit configuration is required to enable component scanning (which leads to auto-wiring)
// @Configuration identifies a class which should be read for Spring configuration data
// Spring boot eliminates this class completely by scanning the classpath for required classes 
@Configuration
@ComponentScan
public class CDPlayerConfig {
}
