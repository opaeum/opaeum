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
import org.junit.Assert;

public class SimpleAsyncShippingController {

	@Inject
	private Session session;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void testSignal() {
		List<Application> roots = session.createQuery("select h from Application h").list();
		Assert.assertTrue(roots.size()>0);
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
		Assert.assertTrue( shipping.isStepActive(SimpleAsyncShippingState.EMAILCUSTOMER) );
		session.flush();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void testReceiveEvent() {
		List<Application> roots = session.createQuery("select h from Application h").list();
		Assert.assertTrue(roots.size()>0);
		Application app = roots.get(0);
		SimpleAsyncShipping shipping = app.getDispatch().getSimpleAsyncShipping().get(0);
		Assert.assertTrue( shipping.isStepActive(SimpleAsyncShippingState.ACTIVITYFINALNODE1) );
		session.flush();
	}	
	
}
