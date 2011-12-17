package org.xsalefter.hibernate101;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PersistenceUtil {

	public static SessionFactory getHibernateSessionfactory() {
		return new Configuration().configure().buildSessionFactory();
	}
	
	public static EntityManagerFactory getJPAEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("example");
	}
}
