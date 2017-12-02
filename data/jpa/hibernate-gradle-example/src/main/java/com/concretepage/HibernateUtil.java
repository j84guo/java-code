package com.concretepage;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	 // shared SessionFactory singleton
     private static SessionFactory sessionFactory;
     
     // static blocks generally initialize static variables 
     static {

     	// configures hibernate for database
    	Configuration configuration = new Configuration().configure();
    	
    	// builder is ussed to get a session factory
    	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        
    	// session factory is used to create sessions 
        sessionFactory = configuration.buildSessionFactory(builder.build());
     }

    // obtain SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
} 
