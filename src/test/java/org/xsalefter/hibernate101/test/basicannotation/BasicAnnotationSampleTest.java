package org.xsalefter.hibernate101.test.basicannotation;

import java.util.List;
import javax.persistence.Basic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xsalefter.hibernate101.PersistenceUtil;
import org.xsalefter.hibernate101.entity.Student;


/**
 * <p>
 * Show how to use {@link Basic} annotation in basic object: {@link String}, 
 * {@link Integer}, etc. {@link Basic} annotation that applied to basic object 
 * mapping need special configuration (byte code instrumentation) when you build 
 * a source code. You could see maven pom xml configuration on this project to 
 * see more.
 * </p>
 * 
 * <p>
 * {@link BasicAnnotationSampleTest#runByUsingJPAEntityManager()} and 
 * {@link BasicAnnotationSampleTest#runByUsingHibernateSession()} show you 
 * when we querying the object, (on this case 's') not all properties loaded.
 * You could see on the query result produced by hibernate querying
 * {@link Student#getId()} and {@link Student#getName()} only:
 * <code><br/>
 * select student0_.student_id as student1_2_, student0_.name as name2_ from student student0_
 * </code><br/>
 * <p>
 * 
 * </p>
 * This is happen because on both method we only <strong>requesting</strong> id 
 * and name:
 * <code><br/>
 * logger.info(">>>>> Student ID: {} - Student Name: {}.", student.getId(), student.getName());
 * </code><br/>
 * </p>
 * 
 * @author xsalefter (xsalefter@gmail.com)
 */
public class BasicAnnotationSampleTest {
	
	private Logger logger = LoggerFactory.getLogger("BasicAnnotationSampleTest >>>>>");

	@Test
	public void runByUsingHibernateSession() {
		SessionFactory sessionFactory = PersistenceUtil.getHibernateSessionfactory();
		Session session = sessionFactory.openSession();
		
		@SuppressWarnings("unchecked")
		List<Student> students = session.
			createQuery("select s from Student s").
			list();
		
		logger.info(">>>>> #runByUsingHibernateSession()");
		for (Student student : students) {
			logger.info(">>>>> Student ID: {} - Student Name: {}.", student.getId(), student.getName());
		}
		
		session.clear();
		session.close();
		sessionFactory.close();
	}
	
	
	@Test
	public void runByUsingJPAEntityManager() {
		EntityManagerFactory entityManagerFactory = PersistenceUtil.getJPAEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		@SuppressWarnings("unchecked")
		List<Student> students = entityManager.
			createQuery("select s from Student s").
			getResultList();
		
		logger.info(">>>>> #runByUsingJPAEntityManager()");
		for (Student student : students) {
			logger.info(">>>>> Student ID: {} - Student Name: {}.", student.getId(), student.getName());
		}
		
		entityManager.clear();
		entityManager.close();
		entityManagerFactory.close();
	}
}
