package net.sf.nakeduml.jbpm.test;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import jbpm.jbpm.Application;
import jbpm.jbpm.OrderX;
import jbpm.jbpm.dispatch.SimpleAsyncShipping;
import jbpm.jbpm.dispatch.SimpleAsyncShippingState;

import org.hibernate.Session;

public class SimpleAsyncShippingController {

	@Inject
	private Session session;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public SimpleAsyncShipping testSignal() {
		List<Application> roots = session.createQuery("select h from Application h").list();
		Application app = roots.get(0);
		for (OrderX order : app.getOrderX()) {
			order.setNeedsShipping(false);
		}
		for (OrderX order : app.getOrderX()) {
			order.setNeedsShipping(true);
			//TODO do with weights to make process wait for multiple signals
			break;
		}
		SimpleAsyncShipping shipping = app.getDispatch().SimpleAsyncShipping();
		shipping.execute();
		session.flush();
		return shipping;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean isActivityFinal() {
		session.clear();
		List<Application> roots = session.createQuery("select h from Application h").list();
		Application app = roots.get(0);
		SimpleAsyncShipping shipping = app.getDispatch().getSimpleAsyncShipping().get(0);
		return shipping.isStepActive(SimpleAsyncShippingState.ACTIVITYFINALNODE1);
	}	
	
}
