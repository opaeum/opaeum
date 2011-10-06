package org.opeum.seam3.persistence;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.hibernate.classic.Session;
import org.jboss.logging.Logger;

@Singleton(name="InitializeHibernate")
@Startup
@TransactionAttribute(TransactionAttributeType.NEVER)
public class InitializeHibernate{
	@Inject
	Logger logger;
	@Inject
	ManagedHibernateSessionFactoryProvider sessionFactory;
	@PostConstruct
	public void init(){
		logger.debug("init");
		//Invoke method on factory to ensure it is loaded
		Session session = sessionFactory.getSessionFactory().openSession();
		session.enableFilter("noDeletedObjects");
		session.close();
	}
	@PreDestroy
	public void destroy(){
		sessionFactory.close();
	}
}