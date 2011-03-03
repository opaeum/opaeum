package net.sf.nakeduml.jbpm.test;

import jbpm.jbpm.Application;
import jbpm.jbpm.Customer;
import jbpm.jbpm.Product;
import jbpm.jbpm.application.OrderProcess;
import jbpm.jbpm.application.OrderProcessState;

import org.junit.Test;

public class ProcessTest {

	@Test
	public void test() {
		Application application = new Application();
		new Product(application); 
		Customer customer = new Customer(application);
		customer.setName("john");
		OrderProcess p = application.OrderProcess(customer);
		p.execute();
		assert p.isStepActive(OrderProcessState.JOINNODE1);
		assert p.isStepActive(OrderProcessState.ACCEPTCALLACTION1);
		p.Operation1();
		assert p.isStepActive(OrderProcessState.ACTIVITYFINALNODE1);
		
	}
	
}
