package net.sf.nakeduml.jbpm.test;

import jbpm.jbpm.Application;
import jbpm.jbpm.Customer;
import jbpm.jbpm.Product;
import jbpm.jbpm.application.SimpleSync1;
import jbpm.jbpm.application.SimpleSync1State;
import junit.framework.Assert;

import org.testng.annotations.Test;

public class SimpleSyncTest {

	@Test
	public void test() {
		Application application = new Application();
		new Product(application); 
		Customer customer = new Customer(application);
		customer.setName("john");
		SimpleSync1 p = application.SimpleSync1(customer);
		p.execute();
		assert p.isStepActive(SimpleSync1State.JOINNODE1);
		assert p.isStepActive(SimpleSync1State.SHIP);
		p.Operation1();
		assert p.isStepActive(SimpleSync1State.ACTIVITYFINALNODE1);
	}

	@Test
	public void testSimpleAction() {
		Application application = new Application();
		Product product = new Product(application);
		product.setName("ssss");
		Customer customer = new Customer(application);
		customer.setName("john");
		Assert.assertEquals("ssssAnother3Another2", application.SimpleSync2(product));
		product = new Product(application);
		product.setName("ss");
		Assert.assertEquals("ssAnother1", application.SimpleSync2(product));
	}

	
}
