package net.sf.nakeduml.ripper;

import javax.inject.Inject;

import jbpm.jbpm.Application;
import jbpm.jbpm.rip.NetworkSoftwareVersion;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
public class RipWebIntegrationTest extends JbpmWebTest {

	@Inject
	private RipTestController ripTestController;
	private static final int MAX_TIME_TO_WAIT = 40000;
	private static final int SLEEP_TIME = 4000;
	private Logger log = LoggerFactory.getLogger(RipWebIntegrationTest.class);
	@Test
	public void testRipProcess() throws InterruptedException {
		Application application = ripTestController.createTestData();
		NetworkSoftwareVersion networkSoftwareVersion = application.getNetwork().iterator().next().getNetworkSoftwareVersion().iterator().next();
		ripTestController.testRipNetwork(networkSoftwareVersion);
		int totalTimeWaited = 0;
		Thread.sleep(SLEEP_TIME);
        while (!ripTestController.assertProcessCompleted(networkSoftwareVersion) && totalTimeWaited < MAX_TIME_TO_WAIT) {
            try {
                log.info("Sleeping for a few seconds, waiting for process to complete. Waited for " + totalTimeWaited + "ms so far ...");
                totalTimeWaited += SLEEP_TIME;
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException ex) {
                log.error("Thread was woken up while sleeping");
                Assert.fail("Thread was woken up while sleeping. Why?");
                ex.printStackTrace();
            }
        }
		Assert.assertTrue(ripTestController.assertProcessCompleted(networkSoftwareVersion));
		//TODO this must be assertProcessCompletedWithSuccess, this is for testing jenkins
		Assert.assertTrue(ripTestController.assertProcessCompletedWithFailure(networkSoftwareVersion));
	}
	
}
