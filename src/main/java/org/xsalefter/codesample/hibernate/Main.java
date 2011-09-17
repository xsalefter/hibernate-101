package org.xsalefter.codesample.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xsalefter.codesample.hibernate.entity.Student;
import org.xsalefter.codesample.hibernate.util.PersistenceUtil;

public class Main {
	
	private Logger logger = LoggerFactory.getLogger(Main.class);
	
	public void runAsJavaPersistence() {
		EntityManagerFactory entityManagerFactory = PersistenceUtil.getJPAEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		@SuppressWarnings("unchecked")
		List<Student> students = entityManager.
			createQuery("select new Student(s.id, s.name) from Student s").
			getResultList();
		
		for (Student student : students) {
			logger.info("Student ID: {} - Student Name: {}.", student.getId(), student.getName());
		}
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	public void runAsNativeHibernate() {
		SessionFactory sessionFactory = PersistenceUtil.getHibernateSessionfactory();
		Session session = sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		List<Student> students = session.
			createQuery("select new Student(s.id, s.name) from Student s").
			list();
		
		for (Student student : students) {
			logger.info("Student ID: {} - Student Name: {}.", student.getId(), student.getName());
		}
		
		session.close();
		sessionFactory.close();
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.runAsNativeHibernate();
		main.runAsJavaPersistence();
		
	}
}
