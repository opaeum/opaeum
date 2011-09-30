package net.sf.opeum.arquillian;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class JavaArchiveTest {

	@Deployment
	public static JavaArchive createTestArchive() {
		return ArquillianUtils.createJavaArchive().addClass(Greeting.class);
	}

	@Inject
	Greeting greeting;

	@Test
	public void shouldBeAbleToInjectBean() throws Exception {
		Assert.assertEquals("halo", greeting.sayHallo());
	}
}
