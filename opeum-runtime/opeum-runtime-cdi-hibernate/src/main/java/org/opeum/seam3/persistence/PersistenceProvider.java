package org.opeum.seam3.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.opeum.hibernate.domain.HibernateCmtPersistence;
import org.opeum.hibernate.domain.HibernateConversationalPersistence;
import org.opeum.hibernate.domain.HibernateUmtPersistence;
import org.opeum.runtime.persistence.CmtPersistence;
import org.opeum.runtime.persistence.ConversationalPersistence;
import org.opeum.runtime.persistence.UmtPersistence;

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
