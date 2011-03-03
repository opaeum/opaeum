package net.sf.nakeduml.jbpm.test;

import java.io.IOException;

import javax.inject.Inject;

import net.sf.nakeduml.arquillian.ArquillianUtils;
import net.sf.nakeduml.test.NakedUtilTestClasses;

import org.hibernate.annotations.common.util.ReflectHelper;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ActivityJbpmTest extends BaseTest {
	@Inject
	private ProcessController processController;
	
	private static final String HORNETQ_JMS_DEPLOYMENT_CONFIG = "hornetq-jms.xml";


	@Deployment
	static public Archive<?> createTestArchive() throws IllegalArgumentException, IOException, ClassNotFoundException {
		WebArchive war = ArquillianUtils.createWarArchive(false);
		war.addWebResource("WEB-INF/beans.xml", "beans.xml");
		war.addWebResource("jbpm-war.hibernate.cfg.xml", "classes/hibernate.cfg.xml");
		war.addWebResource("data.generation.properties", "data.generation.properties");
		war.addWebResource("jbpm/jbpm/application/OrderProcess.rf", "jbpm/jbpm/application/OrderProcess.rf");
		war.addPackages(true, NakedUtilTestClasses.getTestPackages());
		war.addPackages(true, getTestPackages());
		war.addPackage(ReflectHelper.classForName("jbpm" + ".package-info").getPackage());
		war.addManifestResource(HORNETQ_JMS_DEPLOYMENT_CONFIG);
		return war;
	}
	
	@Test
	public void test() {
		processController.testProcess();
		processController.doOperationTest();
	}

}