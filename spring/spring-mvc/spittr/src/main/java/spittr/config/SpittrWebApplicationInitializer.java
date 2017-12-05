package spittr.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// any class which extends AbstractAnnotationConfigDispatcherServletInitializer
// will be used by spring to configure the dispatcher servlet
// ultimately spring looks for a class which implements javax.servlet.ServletContainerInitializer
// a change of inheritance satisfies that when the below class is declared
public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	// indicates the DispatcherServlet will be mapped to all requests
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

    // configures the ContextLoaderListener's application context 
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}
	
	// configures the DispatcherServlet's application context 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}
}
