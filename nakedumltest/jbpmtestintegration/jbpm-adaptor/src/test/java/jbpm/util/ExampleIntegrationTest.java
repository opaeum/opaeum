package jbpm.util;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import jbpm.jbpm.Application;

import org.hibernate.Session;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nakeduml.test.adaptor.ArtifactNames;
import org.nakeduml.test.adaptor.MavenArtifactResolver;

@RunWith(Arquillian.class)
public class ExampleIntegrationTest {
	@Inject
	private Session session;


	@Deployment
	static public Archive<?> createTestArchive() throws IllegalArgumentException, IOException, ClassNotFoundException {
		WebArchive testArchive = (WebArchive) TestUtil.createTestArchive();
		testArchive.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.COMMONS_NET));
		testArchive.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.COMMONS_POOL));
		testArchive.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.JCRAFT_JSCH));
		return testArchive;
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test() {
		List<Application> roots = session.createQuery("select h from Application h").list();
		Assert.assertFalse(roots.size()>0);
	}

}