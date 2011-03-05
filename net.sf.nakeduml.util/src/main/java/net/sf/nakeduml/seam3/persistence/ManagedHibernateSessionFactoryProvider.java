package net.sf.nakeduml.seam3.persistence;

import javax.annotation.PreDestroy;
import javax.inject.Singleton;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Singleton
public class ManagedHibernateSessionFactoryProvider {

	private SessionFactory sessionFactory;
	
	public ManagedHibernateSessionFactoryProvider() {
		super();
		Configuration config = new Configuration();
		config.configure();
		this.sessionFactory = config.buildSessionFactory();
	}
	
	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}
	
	@PreDestroy
	public void close() {
		this.sessionFactory.close();
	}

}
