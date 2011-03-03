package net.sf.nakeduml.jbpm.test;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import jbpm.jbpm.Application;
import jbpm.jbpm.application.OrderProcess;
import jbpm.jbpm.application.OrderProcessState;

import org.hibernate.Session;
import org.junit.Assert;
import org.nakeduml.jbpm5.JbpmKnowledgeSession;

public class ProcessController {

//	@Inject
	private Session session;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void testProcess() {
		session = JbpmKnowledgeSession.getInstance().getHibernateSession();

		List<Application> roots = session.createQuery("select h from Application h").list();
		Assert.assertTrue(roots.size()>0);
		Application app = roots.get(0);
		OrderProcess p = app.OrderProcess(app.getCustomer().iterator().next());
		p.execute();
		assert p.isStepActive(OrderProcessState.JOINNODE1);
		assert p.isStepActive(OrderProcessState.ACCEPTCALLACTION1);
//		p.Operation1();
//		assert p.isStepActive(OrderProcessState.ACTIVITYFINALNODE1);			
		session.flush();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void doOperationTest() {
		session = JbpmKnowledgeSession.getInstance().getHibernateSession();
		List<Application> roots = session.createQuery("select h from Application h").list();
		Assert.assertTrue(roots.size()>0);
		Application app = roots.get(0);
		OrderProcess p = app.getOrderProcess().get(0);
		p.Operation1();
		assert p.isStepActive(OrderProcessState.ACTIVITYFINALNODE1);			
		session.flush();
	}
	
}
