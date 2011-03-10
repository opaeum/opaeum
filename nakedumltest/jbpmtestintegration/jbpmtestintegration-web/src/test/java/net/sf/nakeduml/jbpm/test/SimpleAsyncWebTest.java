package net.sf.nakeduml.jbpm.test;

import javax.inject.Inject;

import jbpm.jbpm.dispatch.SimpleAsyncShipping;
import jbpm.jbpm.dispatch.SimpleAsyncShippingState;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nakeduml.util.BaseTest;

@RunWith(Arquillian.class)
public class SimpleAsyncWebTest extends BaseTest {
	@Inject
	private SimpleAsyncShippingController processController;
	private static final String HORNETQ_JMS_DEPLOYMENT_CONFIG = "hornetq-jms.xml";

	@Test
	public void testSignal() throws InterruptedException {
		SimpleAsyncShipping shipping = processController.testSignal();
		Assert.assertTrue( shipping.isStepActive(SimpleAsyncShippingState.EMAILCUSTOMER) );
		Thread.sleep(2000);
		Assert.assertTrue( processController.isActivityFinal() );
	}	

}