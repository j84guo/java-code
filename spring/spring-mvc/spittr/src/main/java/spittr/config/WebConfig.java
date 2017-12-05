/*
*
* declares DispatcherServlet's configuration
*
*/

package spittr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration // identifies bean configuration class
@EnableWebMvc // enables spring mvc
@ComponentScan("spitter.web") // component scans the spitter.web package
public class WebConfig extends WebMvcConfigurerAdapter { 
	
	// configures a jsp view resolver
	// wraps view names with pregix and suffix, e.g. "home" becomes "/WEB-INF/views/home.jsp"
	// god why
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}

    // configures static content handling
	// forwards static resource requests to dispatcher servlet's default servlet 
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}