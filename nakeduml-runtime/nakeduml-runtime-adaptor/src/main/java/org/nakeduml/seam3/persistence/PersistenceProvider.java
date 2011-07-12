package org.nakeduml.seam3.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.nakeduml.environment.CmtPersistence;
import org.nakeduml.environment.ConversationalPersistence;
import org.nakeduml.environment.UmtPersistence;
import org.nakeduml.hibernate.domain.HibernateCmtPersistence;
import org.nakeduml.hibernate.domain.HibernateConversationalPersistence;
import org.nakeduml.hibernate.domain.HibernateUmtPersistence;

@ApplicationScoped
public class PersistenceProvider{
	@Inject
	ManagedHibernateSessionFactoryProvider  initializeHibernate;
	@Produces
	@Dependent
	public ConversationalPersistence getPersistence(){
		return new HibernateConversationalPersistence(initializeHibernate.getSessionFactory().openSession());
	}
	@Produces
	@Dependent
	public UmtPersistence getUmtPersistence(){
		return new HibernateUmtPersistence(initializeHibernate.getSessionFactory().openSession());
	}
	@Produces
	@Dependent
	public CmtPersistence getCmtPersistence(){
		return new HibernateCmtPersistence(initializeHibernate.getSessionFactory().getCurrentSession());
	}
	
}
