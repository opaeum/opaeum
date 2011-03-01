package net.sf.nakeduml.jbpm.test;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import jbpm.jbpm.Application;
import jbpm.jbpm.OrderX;
import net.sf.nakeduml.arquillian.ArquillianUtils;
import net.sf.nakeduml.arquillian.ArtifactNames;
import net.sf.nakeduml.arquillian.MavenArtifactResolver;
import net.sf.nakeduml.test.NakedUtilTestClasses;

import org.hibernate.Session;
import org.hibernate.annotations.common.util.ReflectHelper;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ActivityJbpmTest extends BaseTest {
	@Inject
	private Session session;
	private static final String HORNETQ_JMS_DEPLOYMENT_CONFIG = "hornetq-jms.xml";


	@Deployment
	static public Archive<?> createTestArchive() throws IllegalArgumentException, IOException, ClassNotFoundException {
		WebArchive war = ArquillianUtils.createWarArchive(false);
		war.addWebResource("WEB-INF/beans.xml", "beans.xml");
		war.addWebResource("hibernate.cfg.xml", "classes/hibernate.cfg.xml");
		war.addWebResource("data.generation.properties", "data.generation.properties");
		war.addClasses(NakedUtilTestClasses.getTestClasses());
		war.addPackages(true, getTestPackages());
		war.addPackage(ReflectHelper.classForName("jbpm" + ".package-info").getPackage());
		war.addManifestResource(HORNETQ_JMS_DEPLOYMENT_CONFIG);
		return war;
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void test() {
		List<Application> roots = session.createQuery("select h from Application h").list();
		Assert.assertTrue(roots.size()>0);
		Application app = roots.get(0);
		app.OrderProcess(app.getCustomer().iterator().next());
	}

}