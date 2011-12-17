package org.xsalefter.hibernate101.test.sample;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xsalefter.hibernate101.PersistenceUtil;
import org.xsalefter.hibernate101.entity.Student;
import org.xsalefter.hibernate101.test.SuperTest;


/**
 * Show how to use {@link Basic} annotation. {@link Basic} annotation need 
 * special configuration (byte code instrumentation) when you build a source 
 * code. You could see maven pom xml configuration on this project to see 
 * more.
 * 
 * @author xsalefter (xsalefter@gmail.com)
 */
public class BasicAnnotationSampleTest extends SuperTest {
	
	private Logger logger = LoggerFactory.getLogger(BasicAnnotationSampleTest.class);

	@Test
	public void runByUsingHibernateSession() {
		Session session = super.sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		List<Student> students = session.
			createQuery("select s from Student s").
			list();
		
		logger.info(">>>>> #runByUsingHibernateSession()");
		for (Student student : students) {
			logger.info(">>>>> Student ID: {} - Student Name: {}.", student.getId(), student.getName());
		}
		
		session.close();
	}
	
	
	@Test
	public void runByUsingJPAEntityManager() {
		EntityManager entityManager = PersistenceUtil.getJPAEntityManagerFactory().createEntityManager();
		
		@SuppressWarnings("unchecked")
		List<Student> students = entityManager.
			createQuery("select s from Student s").
			getResultList();
		
		logger.info(">>>>> #runByUsingJPAEntityManager()");
		for (Student student : students) {
			logger.info(">>>>> Student ID: {} - Student Name: {}.", student.getId(), student.getName());
		}
		
		entityManager.close();
	}
}
