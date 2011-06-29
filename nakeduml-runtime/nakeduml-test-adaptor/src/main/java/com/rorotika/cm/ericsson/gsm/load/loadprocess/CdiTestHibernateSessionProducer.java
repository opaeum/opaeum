package com.rorotika.cm.ericsson.gsm.load.loadprocess;

import javax.enterprise.inject.Produces;

import org.hibernate.Session;
import org.nakeduml.environment.cdi.test.CdiTestEnvironment;
import org.nakeduml.seam3.persistence.UserManagedSession;

public class CdiTestHibernateSessionProducer{
	@Produces
	public Session createSession1(){
		return CdiTestEnvironment.getInstance().getHibernateSessionFactory().openSession();
	}
	@Produces
	@UserManagedSession
	public Session createSession2(){
		return CdiTestEnvironment.getInstance().getHibernateSessionFactory().openSession();
	}
//	@DependentScopedSession
//	@Produces
//	@Dependent
//	public Session createSession2() {
//		return CdiTestEnvironment.getInstance().getHibernateSessionFactory().openSession();
//	}
//	@TransactionScopedSession
//	@Produces
//	@TransactionScoped
//	public Session createSession3() {
//		return CdiTestEnvironment.getInstance().getHibernateSessionFactory().openSession();
//	}
	
}