package org.opaeum.seam3.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.opaeum.hibernate.domain.HibernateCmtPersistence;
import org.opaeum.hibernate.domain.HibernateConversationalPersistence;
import org.opaeum.hibernate.domain.HibernateUmtPersistence;
import org.opaeum.runtime.persistence.CmtPersistence;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.UmtPersistence;

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
