package net.sf.nakeduml.jbpm.test;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import jbpm.jbpm.TheBoss;

import net.sf.nakeduml.arquillian.ArquillianUtils;
import net.sf.nakeduml.test.NakedUtilTestClasses;

import org.hibernate.Session;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class DummyTest extends BaseTest {
	@Inject
	private Session session;


	@Deployment
	static public Archive<?> createTestArchive() throws IllegalArgumentException, IOException, ClassNotFoundException {
		WebArchive war = ArquillianUtils.createWarArchive(false);
		war.addWebResource("WEB-INF/beans.xml", "beans.xml");
		war.addWebResource("hibernate.cfg.xml", "classes/hibernate.cfg.xml");
		war.addWebResource("data.generation.properties", "data.generation.properties");
//		war.addClasses(NakedUtilTestClasses.getTestClasses());
		war.addPackages(true, getTestPackages());
		return war;
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void test() {
		List<TheBoss> roots = session.createQuery("select h from TheBoss h").list();
		Assert.assertTrue(roots.size()>0);
	}

}