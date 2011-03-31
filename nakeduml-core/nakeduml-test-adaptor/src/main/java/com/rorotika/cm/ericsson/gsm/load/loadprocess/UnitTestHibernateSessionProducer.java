package com.rorotika.cm.ericsson.gsm.load.loadprocess;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

import org.hibernate.Session;
import org.nakeduml.environment.cdi.test.CdiTestEnvironment;

public class UnitTestHibernateSessionProducer{
	@Produces
	@RequestScoped
	public Session getSession(){
		return CdiTestEnvironment.getInstance().getHibernateSessionFactory().openSession();
	}
}