package org.opeum.jbpmtestintegration.test;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import jbpm.jbpm.Application;
import jbpm.jbpm.application.SimpleSync1;
import jbpm.jbpm.application.SimpleSync1State;

import org.hibernate.Session;

public class SimpleSyncController {

	@Inject
	private Session session;
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean testProcessAndTestForJOINNODE1andSHIP() {
		List<Application> roots = session.createQuery("select h from Application h").list();
		Application app = roots.get(0);
		SimpleSync1 p = app.SimpleSync1(app.getCustomer().iterator().next());
		p.execute();
		session.flush();
		return p.isStepActive(SimpleSync1State.JOINNODE1) && p.isStepActive(SimpleSync1State.SHIP);
	}
	
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean doOperationTestAndTestForActivityFinalNode1() {
		List<Application> roots = session.createQuery("select h from Application h").list();
		Application app = roots.get(0);
		SimpleSync1 p = app.getSimpleSync1().get(0);
		p.Operation1();
		session.flush();
		return p.isStepActive(SimpleSync1State.ACTIVITYFINALNODE1);
	}

	
}
