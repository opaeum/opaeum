package org.nakeduml.arquillian;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class EarArchiveTest {

	public static Class<?>[] getTestClasses() {
		return new Class[] { Greeting.class, EarArchiveTest.class };
	}

	@Deployment
	public static Archive<?> createTestArchive() {
		JavaArchive java = ArquillianUtils.createJavaArchive();
		java.addClasses(getTestClasses());
		EnterpriseArchive ear = ArquillianUtils.createEarArchive();
		ear.addModule(java);
		ear.addModule(ArquillianUtils.createTestArchive());
		ear.addApplicationResource("META-INF/application.xml", "application.xml");
		return ear;
	}

	@EJB
	Greeting greeting;
	
	@Test
	public void test1() {
		Assert.assertEquals("halo", greeting.sayHallo());
	}
}
