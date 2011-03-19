package net.sf.nakeduml.jbpm.test;

import javax.inject.Inject;

import jbpm.jbpm.dispatch.SimpleAsyncShipping;
import jbpm.jbpm.dispatch.SimpleAsyncShippingState;
import net.sf.nakeduml.ripper.JbpmWebTest;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class SimpleAsyncWebIntegrationTest extends JbpmWebTest {
	@Inject
	private SimpleAsyncShippingController processController;

	@Test
	public void testSignal() throws InterruptedException {
		SimpleAsyncShipping shipping = processController.testSignal();
		Assert.assertTrue( shipping.isStepActive(SimpleAsyncShippingState.EMAILCUSTOMER) );
		Thread.sleep(2000);
		Assert.assertTrue( processController.isActivityFinal() );
	}	

}