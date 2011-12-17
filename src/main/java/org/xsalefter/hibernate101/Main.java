package org.xsalefter.hibernate101;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xsalefter.hibernate101.entity.Student;

public class Main {
	
	private Logger logger = LoggerFactory.getLogger(Main.class);
	
	public void runAsJavaPersistence() {
		EntityManagerFactory entityManagerFactory = PersistenceUtil.getJPAEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		@SuppressWarnings("unchecked")
		List<Student> students = entityManager.
			createQuery("select s from Student s").
			getResultList();
		
		logger.info(">>>>> runAsJavaPersistence()");
		for (Student student : students) {
			logger.info(">>>>> Student ID: {} - Student Name: {}.", student.getId(), student.getName());
		}
		
		entityManager.close();
		entityManagerFactory.close();
	}
	
	public void runAsNativeHibernate() {
		SessionFactory sessionFactory = PersistenceUtil.getHibernateSessionfactory();
		Session session = sessionFactory.openSession();
		
		logger.info(">>>>> runAsNativeHibernate()");
		
		@SuppressWarnings("unchecked")
		List<Student> students = session.
			createQuery("select s from Student s").
			list();
		
		for (Student student : students) {
			logger.info(">>>>> Student ID: {} - Student Name: {}.", student.getId(), student.getName());
		}
		
		session.close();
		sessionFactory.close();
	}
	
	public void findOnly() {
		EntityManagerFactory factory = PersistenceUtil.getJPAEntityManagerFactory();
		EntityManager entityManager = factory.createEntityManager();
		
		Student student = entityManager.find(Student.class, 1L);
		logger.info(">>>>> Student Name: {} -- Student Email: {}", student.getName(), student.getEmail());
		
		entityManager.close();
		factory.close();
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.runAsNativeHibernate();
		main.runAsJavaPersistence();
		main.findOnly();
		
	}
}
