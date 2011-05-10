package com.rorotika.cm.ericsson.gsm.load.loadprocess;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jboss.seam.solder.core.ExtensionManaged;
import org.jboss.seam.transaction.TransactionScoped;
import org.nakeduml.environment.cdi.test.CdiTestEnvironment;
import org.nakeduml.seam3.persistence.DependentScopedSession;
import org.nakeduml.seam3.persistence.TransactionScopedSession;

public class CdiTestHibernateSessionProducer{
	@Produces
	@RequestScoped
	public Session createSession1(){
		return CdiTestEnvironment.getInstance().getHibernateSessionFactory().openSession();
	}
	@DependentScopedSession
	@Produces
	@Dependent
	public Session createSession2() {
		return CdiTestEnvironment.getInstance().getHibernateSessionFactory().openSession();
	}
	@TransactionScopedSession
	@Produces
	@TransactionScoped
	public Session createSession3() {
		return CdiTestEnvironment.getInstance().getHibernateSessionFactory().openSession();
	}
	
}