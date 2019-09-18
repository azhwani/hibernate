package com.test.app;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.maven.pojos.Employe;

/**
 * Hello world!
 *
 */
public class AppHibernate 
{
    public static void main( String[] args ) {
    	
    	 /* SESSION FACTORY */
        SessionFactory sessFact = HibernateUtil.getSessionFactory();
		Session session = sessFact.getCurrentSession();
		//Transaction tr = session.beginTransaction();
		
		/*
    Employe emp = new Employe();
		emp.setFirstname("John");
		emp.setLastname("Snow");
		emp.setEmail("john.snow@gmail.com");
		emp.setReg_date(new Date());
		session.save(emp);
		tr.commit();
    */
		
		List<String> names = new ArrayList<String>();
		names.add("Richard");
		names.add("ahmed");
		
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Employe> myemps = session.createCriteria(Employe.class).add(Restrictions.not(Restrictions.in("firstname", names))).list();
		
		for(Employe empl:myemps){
			System.out.println(empl.getEmail());
		}
    
		sessFact.close();
        
    }
    
}
