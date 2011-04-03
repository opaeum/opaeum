package org.nakeduml.jbpmtestintegration.test;

import java.io.IOException;

import javax.inject.Inject;

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

@RunWith(Arquillian.class)
public class SimpleSyncWebIntegrationTest {
	@Inject
	private SimpleSyncController processController;
	
	@Deployment
	static public Archive<?> createTestArchive() throws IllegalArgumentException, IOException, ClassNotFoundException {
		WebArchive war = (WebArchive) NakedUmlTestUtil.createTestArchive();
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.SEAM_SERVLET_API));
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.SEAM_SERVLET_IMPL));
		war.addClass(org.nakeduml.jbpmtestintegration.util.jbpm.adaptor.JbpmKnowledgeBase.class);
		war.addClass(SimpleSyncController.class);
		war.addClass(SimpleSyncWebIntegrationTest.class);
		war.addClass(StartDataGeneration.class);
		return war;
	}
	
	@Test
	public void test() {
		Assert.assertTrue(  processController.testProcessAndTestForJOINNODE1andSHIP() );
		Assert.assertTrue(  processController.doOperationTestAndTestForActivityFinalNode1() );			
	}

}