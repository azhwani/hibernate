package com.test.app;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory initSessionFactory() {

		try {
			sessionFactory = new Configuration().configure("/META-INF/hibernate.cfg.xml").buildSessionFactory();
			return sessionFactory;
		} 
		catch (Throwable th) {
			System.err.println("Enitial SessionFactory creation failed!" + th);
			throw new ExceptionInInitializerError(th);
		}

	}
	
	public static SessionFactory init2SessionFactory(){
		
		try {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure("META-INF/hibernate.cfg.xml").build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			sessionFactory = metaData.getSessionFactoryBuilder().build();
			return sessionFactory;
			
		}catch (Throwable th) {
			System.err.println("Enitial SessionFactory creation failed!" + th);
			throw new ExceptionInInitializerError(th);
		}
		
	}
	
	public static SessionFactory init3SessionFactory() {

		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		hibernateProp.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernateDB");
		hibernateProp.put("hibernate.connection.username", "root");
		hibernateProp.put("hibernate.connection.password", "root");
		hibernateProp.put("dialect", "org.hibernate.dialect.MySQLDialect");
		hibernateProp.put("show_sql", "true");
		hibernateProp.put("hibernate.current_session_context_class", "thread");
		
		try {
			sessionFactory = new Configuration().addProperties(hibernateProp).configure().buildSessionFactory();
			return sessionFactory;
		} 
		catch (Throwable th) {
			System.err.println("Enitial SessionFactory creation failed!" + th);
			throw new ExceptionInInitializerError(th);
		}

	}
	
	public static SessionFactory init4SessionFactory() {

		Map<String,String> hibernateProp = new HashMap<String,String>();
		/*hibernateProp.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		hibernateProp.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernateDB");
		hibernateProp.put("hibernate.connection.username", "root");
		hibernateProp.put("hibernate.connection.password", "root");
		hibernateProp.put("dialect", "org.hibernate.dialect.MySQLDialect");
		hibernateProp.put("show_sql", "true");
		hibernateProp.put("hibernate.current_session_context_class", "thread");*/
		
		hibernateProp.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
		hibernateProp.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernateDB");
		hibernateProp.put(Environment.USER, "root");
		hibernateProp.put(Environment.PASS, "root");
		hibernateProp.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
		hibernateProp.put(Environment.SHOW_SQL, "true");
		hibernateProp.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
		
		try {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().applySettings(hibernateProp).configure().build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
			sessionFactory = metaData.getSessionFactoryBuilder().build();
			
			return sessionFactory;
		} 
		catch (Throwable th) {
			System.err.println("Enitial SessionFactory creation failed!" + th);
			throw new ExceptionInInitializerError(th);
		}

	}
	
	public static SessionFactory getSessionFactory() {
		return initSessionFactory();
	}
	
	public static SessionFactory get2SessionFactory() {
		return init2SessionFactory();
	}
	
	public static SessionFactory get3SessionFactory() {
		return init3SessionFactory();
	}
	
	public static SessionFactory get4SessionFactory() {
		return init4SessionFactory();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
