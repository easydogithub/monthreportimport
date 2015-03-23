package com.revenco.monthreportimport.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			Configuration cfg = new Configuration().configure();
			SessionFactory sf = cfg
					.buildSessionFactory(new ServiceRegistryBuilder()
							.applySettings(cfg.getProperties()).build());
			return sf;
			/*
			 * return new Configuration().configure().buildSessionFactory( new
			 * StandardServiceRegistryBuilder().build());
			 */
		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}

}
