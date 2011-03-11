package net.sf.nakeduml.jbpm.test;

import jbpm.jbpm.Application;
import jbpm.jbpm.Customer;
import jbpm.jbpm.Dispatch;
import jbpm.jbpm.OrderX;
import jbpm.jbpm.Product;
import jbpm.jbpm.dispatch.SimpleAsyncShipping;
import jbpm.jbpm.dispatch.SimpleAsyncShippingState;

import org.junit.Test;
import org.nakeduml.environment.Environment;
import org.nakeduml.environment.ISignalDispatcher;

public class SimpleAsyncTest extends BaseTest {

	@Test
	public void testSignal() throws InterruptedException {
		Application application = new Application();
		application.setName("cmApplication");
		Customer customer = new Customer(application);
		customer.setName("johnny");
		Product product = new Product(application);
		product.setName("Desire");
		OrderX orderX = new OrderX(application);
		orderX.setName("orderX1");
		orderX.setCustomer(customer);
		orderX.setNeedsShipping(true);
		Dispatch dispatch = new Dispatch(application);
		dispatch.setName("dispatch1");
		SimpleAsyncShipping simpleAsyncShipping = dispatch.SimpleAsyncShipping();
		simpleAsyncShipping.execute();
		simpleAsyncShipping.isStepActive(SimpleAsyncShippingState.EMAILCUSTOMER);
		Environment.getInstance().getComponent(ISignalDispatcher.class).deliverAllPendingSignals();
		simpleAsyncShipping.isStepActive(SimpleAsyncShippingState.ACTIVITYFINALNODE1);
	}	

}