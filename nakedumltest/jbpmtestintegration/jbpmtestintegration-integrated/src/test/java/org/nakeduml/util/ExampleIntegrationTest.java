package org.nakeduml.util;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import jbpm.jbpm.Application;
import jbpm.jbpm.nodedefinition.EISConnection;

import org.hibernate.Session;
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
public class ExampleIntegrationTest extends BaseTest {
	@Inject
	private Session session;


	@Deployment
	static public Archive<?> createTestArchive() throws IllegalArgumentException, IOException, ClassNotFoundException {
		WebArchive war = ArquillianUtils.createWarArchive(false);
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.COMMONS_NET));
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.COMMONS_POOL));
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.JCRAFT_JSCH));
		war.addWebResource("META-INF/beans.xml", "beans.xml");
		war.addWebResource("jbpmtestintegration-hibernate.cfg.xml", "classes/hibernate.cfg.xml");
		war.addWebResource("data.generation.properties", "data.generation.properties");
		war.addWebResource("nakeduml.env.properties", "nakeduml.env.properties");
		
		war.addPackages(true, NakedUtilTestClasses.getTestPackages());
		war.addPackages(true, getTestPackages());
		
		return war;
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void test() {
		List<Application> roots = session.createQuery("select h from Application h").list();
		Assert.assertFalse(roots.size()>0);
	}

}