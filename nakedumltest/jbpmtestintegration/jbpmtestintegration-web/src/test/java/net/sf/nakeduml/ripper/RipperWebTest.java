package net.sf.nakeduml.ripper;

import javax.inject.Inject;

import jbpm.jbpm.Application;
import jbpm.jbpm.rip.Network;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class RipperWebTest extends JbpmWebTest {

	@Inject
	private RipTestController ripTestController;

	@Test
	public void testRipProcess() throws InterruptedException {
		Application application = ripTestController.createTestData();
		Network network = application.getNetwork().iterator().next();
		ripTestController.testRipNetwork(network);
		Thread.sleep(2000);
		Assert.assertTrue(ripTestController.assertProcessCompleted(network));
	}
}
