package net.sf.nakeduml.ripper;

import javax.inject.Inject;

import jbpm.jbpm.Application;
import jbpm.jbpm.rip.Network;
import jbpm.jbpm.rip.NetworkSoftwareVersion;

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
		NetworkSoftwareVersion networkSoftwareVersion = application.getNetwork().iterator().next().getNetworkSoftwareVersion().iterator().next();
		ripTestController.testRipNetwork(networkSoftwareVersion);
		Thread.sleep(2000);
		Assert.assertTrue(ripTestController.assertProcessCompleted(networkSoftwareVersion));
	}
}
