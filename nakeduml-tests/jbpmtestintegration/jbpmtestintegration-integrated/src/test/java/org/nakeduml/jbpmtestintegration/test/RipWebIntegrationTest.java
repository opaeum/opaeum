package org.nakeduml.jbpmtestintegration.test;

import java.io.IOException;

import javax.inject.Inject;

import jbpm.jbpm.Application;
import jbpm.jbpm.nodedefinition.INodeDefinitionFactory;
import jbpm.jbpm.nodedefinition.NodeDefinitionFactory;
import jbpm.jbpm.nodedefinition.RipHelperImpl;
import jbpm.jbpm.rip.NetworkSoftwareVersion;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nakeduml.jbpmtestintegration.util.NakedUmlTestUtil;
import org.nakeduml.test.adaptor.ArtifactNames;
import org.nakeduml.test.adaptor.MavenArtifactResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Arquillian.class)
public class RipWebIntegrationTest  {

	@Inject
	private RipTestController ripTestController;
	private static final int MAX_TIME_TO_WAIT = 40000;
	private static final int SLEEP_TIME = 4000;
	private Logger log = LoggerFactory.getLogger(RipWebIntegrationTest.class);
	
	@Deployment
	static public Archive<?> createTestArchive() throws IllegalArgumentException, IOException, ClassNotFoundException {
		WebArchive war = (WebArchive) NakedUmlTestUtil.createTestArchive();
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.SEAM_SERVLET_API));
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.SEAM_SERVLET_IMPL));
		war.addClass(org.nakeduml.jbpmtestintegration.util.jbpm.adaptor.JbpmKnowledgeBase.class);
		war.addClass(RipWebIntegrationTest.class);
		war.addClass(RipTestController.class);
		war.addClass(StartDataGeneration.class);
		war.addClass(INodeDefinitionFactory.class);
		war.addClass(NodeDefinitionFactory.class);
		war.addClass(RipHelperImpl.class);
		return war;
	}
	
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
		Assert.assertTrue(ripTestController.assertProcessCompletedWithSuccess(networkSoftwareVersion));
	}
	
}
