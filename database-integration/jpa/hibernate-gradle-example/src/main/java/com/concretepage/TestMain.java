package com.concretepage;

import org.hibernate.Session;

public class TestMain {

  public static void main(String[] args) {

	 // create person
	 Person person = new Person();
	 person.setId(1);
	 person.setName("Concretepage");

	 // use a lightwright session (created and destroyed as needed)
	 Session session = HibernateUtil.getSessionFactory().openSession();
	 session.beginTransaction();
	 session.save(person);
	 session.getTransaction().commit();
	 session.close();

   session = HibernateUtil.getSessionFactory().openSession();
	 session.beginTransaction();
   person.setName("Updated Name");
   session.update(person);
	 session.getTransaction().commit();
	 session.close();

   session = HibernateUtil.getSessionFactory().openSession();
	 session.beginTransaction();
   person.setName("Updated Name");
   person = (Person)session.get(Person.class, 1);
	 session.getTransaction().commit();
	 session.close();

   session = HibernateUtil.getSessionFactory().openSession();
	 session.beginTransaction();
   session.delete(person);
	 session.getTransaction().commit();
	 session.close();

   System.out.println(person.getId() + " " + person.getName());

	 // debug
	 System.out.println("Done");
	 System.exit(0);
  }
}
