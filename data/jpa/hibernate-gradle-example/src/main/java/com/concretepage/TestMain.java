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

	 // debug
	 System.out.println("Done");
	 System.exit(0);
  }
}
