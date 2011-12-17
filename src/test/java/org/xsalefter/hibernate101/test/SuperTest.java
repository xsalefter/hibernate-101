package org.xsalefter.hibernate101.test;

import javax.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.xsalefter.hibernate101.PersistenceUtil;

/**
 * Super class for all test classes.
 *  
 * @author xsalefter (xsalefter@gmail.com)
 */
public class SuperTest extends Object {
	
	protected SessionFactory sessionFactory;
	protected EntityManagerFactory entityManagerFactory;
	
	protected SuperTest() {
		this.sessionFactory = PersistenceUtil.getHibernateSessionfactory();
		this.entityManagerFactory = PersistenceUtil.getJPAEntityManagerFactory();
	}
	
}
