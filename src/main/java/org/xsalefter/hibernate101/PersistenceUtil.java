package org.xsalefter.hibernate101;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.MappedSuperclass;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public class PersistenceUtil {

	/**
	 * Since Hibernate 4.0 {@link Configuration#configure#buildSessionFactory()} 
	 * is deprecated. 
	 * 
	 * @return {@link SessionFactory} object.
	 */
	public static SessionFactory getHibernateSessionfactory() {
		Configuration configuration = new Configuration();
		
		for (Class<?> clazz : scanEntityClasses()) {
			configuration.addAnnotatedClass(clazz);
		}
		
		configuration.addResource("hibernate.cfg.xml");
		
		@SuppressWarnings("deprecation")
		final SessionFactory factory = configuration.
			configure().
			buildSessionFactory();
		
		return factory;
	}
	
	@SuppressWarnings("deprecation")
	public static EntityManagerFactory getJPAEntityManagerFactory() {
		org.hibernate.ejb.Ejb3Configuration configuration = 
			new org.hibernate.ejb.Ejb3Configuration();
		
		for (Class<?> clazz : scanEntityClasses()) {
			configuration.addAnnotatedClass(clazz);
		}
		
		final EntityManagerFactory factory = configuration.
				configure("hibernate.cfg.xml").
				buildEntityManagerFactory();
		
		return factory;
	}
	
	@SuppressWarnings("unchecked")
	public static Set<Class<?>> scanEntityClasses() {
		Set<Class<?>> result = new HashSet<Class<?>>();
		
		Set<URL> packages = ClasspathHelper.forPackage("org.xsalefter.hibernate101.entity");
		
		Set<URL> entityAnnotation = new HashSet<URL>();
		entityAnnotation.add(ClasspathHelper.forClass(Entity.class));
		entityAnnotation.add(ClasspathHelper.forClass(MappedSuperclass.class));
		entityAnnotation.add(ClasspathHelper.forClass(Embeddable.class));
		
		ConfigurationBuilder builder = new ConfigurationBuilder().
				addUrls(packages, entityAnnotation)
				.setScanners(
					new ResourcesScanner(), 
					new TypeAnnotationsScanner(),
					new SubTypesScanner()
				);
		
		Reflections reflections = new Reflections(builder);
		
		result.addAll(reflections.getTypesAnnotatedWith(Entity.class));
		result.addAll(reflections.getTypesAnnotatedWith(MappedSuperclass.class));
		result.addAll(reflections.getTypesAnnotatedWith(Embeddable.class));
		
		return result;
	}
}
