package org.xsalefter.hibernate101;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PersistenceUtil {

	/**
	 * Since Hibernate 4.0 {@link Configuration#configure#buildSessionFactory()} 
	 * is deprecated. 
	 * 
	 * @return {@link SessionFactory} object.
	 */
	@SuppressWarnings("deprecation")
	public static SessionFactory getHibernateSessionfactory() {
		return new Configuration().configure().buildSessionFactory();
	}
	
	public static EntityManagerFactory getJPAEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("example");
	}
}
