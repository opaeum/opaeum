package net.sf.nakeduml.ripper;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import jbpm.jbpm.Application;
import jbpm.jbpm.nodedefinition.EISConnection;
import jbpm.jbpm.rip.Network;

import org.hibernate.Session;
import org.hibernate.annotations.common.util.ReflectHelper;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nakeduml.test.NakedUtilTestClasses;
import org.nakeduml.test.adaptor.ArquillianUtils;
import org.nakeduml.test.adaptor.ArtifactNames;
import org.nakeduml.test.adaptor.MavenArtifactResolver;

@RunWith(Arquillian.class)
public class RipperWebTest extends JbpmWebTest {

	@Inject
	private Session session;
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
