package net.sf.nakeduml.jbpm.test;

import java.io.IOException;

import javax.inject.Inject;

import org.hibernate.annotations.common.util.ReflectHelper;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nakeduml.test.NakedUtilTestClasses;
import org.nakeduml.test.adaptor.ArquillianUtils;

@RunWith(Arquillian.class)
public class SignalTest extends BaseTest {
	@Inject
	private ProcessController processController;
	
	private static final String HORNETQ_JMS_DEPLOYMENT_CONFIG = "hornetq-jms.xml";


	@Deployment
	static public Archive<?> createTestArchive() throws IllegalArgumentException, IOException, ClassNotFoundException {
		WebArchive war = ArquillianUtils.createWarArchive(false);
		war.addWebResource("WEB-INF/beans.xml", "beans.xml");
		war.addWebResource("jbpmtestintegration-hibernate.cfg.xml", "classes/hibernate.cfg.xml");
		war.addWebResource("data.generation.properties", "data.generation.properties");
		war.addWebResource("nakeduml.env.properties", "nakeduml.env.properties");
		war.addWebResource("jbpm/jbpm/application/SimpleSync1.rf", "jbpm/jbpm/application/SimpleSync1.rf");
		war.addWebResource("jbpm/jbpm/dispatch/SimpleAsyncShipping.rf", "jbpm/jbpm/dispatch/SimpleAsyncShipping.rf");
		war.addPackages(true, NakedUtilTestClasses.getTestPackages());
		war.addPackages(true, getTestPackages());
		war.addPackage(ReflectHelper.classForName("org.nakeduml.util.hibernate.adaptor.package-info").getPackage());
		war.addManifestResource(HORNETQ_JMS_DEPLOYMENT_CONFIG);
		return war;
	}
	
	@Test
	public void test() {
		processController.testSignal();
	}

}