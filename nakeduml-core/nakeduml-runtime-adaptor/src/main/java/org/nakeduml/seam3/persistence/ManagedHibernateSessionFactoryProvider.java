package org.nakeduml.seam3.persistence;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.ApplicationScoped;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Startup
@ApplicationScoped
public class ManagedHibernateSessionFactoryProvider {

	private SessionFactory sessionFactory;

	@PostConstruct
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public void init() {
		Configuration config = new Configuration();
		config.configure();
		try {
			this.sessionFactory = config.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public SessionFactory getSessionFactory() {
		return this.sessionFactory;
	}

	@PreDestroy
	public void close() {
		this.sessionFactory.close();
	}

}
