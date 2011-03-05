package net.sf.nakeduml.seam3.persistence;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.hibernate.Session;

@Startup
@Singleton(name="InitializeHibernate")
@TransactionManagement(TransactionManagementType.BEAN)
public class InitializeHibernate {

	@DependentScopedSession
	@Inject
	Session session;
	
	@PostConstruct
	public void init() {
		System.out.println("init");
	}
	
	//This is to ensure closed is called and the tables dropped
	@PreDestroy
	public void destroy() {
		session.getSessionFactory().close();
	}
}
