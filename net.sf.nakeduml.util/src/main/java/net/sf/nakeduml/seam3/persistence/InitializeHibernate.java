package net.sf.nakeduml.seam3.persistence;

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

	@Inject
	Session session;
	
}
