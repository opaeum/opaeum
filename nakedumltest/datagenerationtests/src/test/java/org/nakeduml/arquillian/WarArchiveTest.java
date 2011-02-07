package org.nakeduml.arquillian;

import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class WarArchiveTest {

	@Deployment
	public static WebArchive createTestArchive() {
		return ArquillianUtils.createWarArchive(true).addClass(Greeting.class);
	}

	@Inject
	Greeting greeting;

	@Test
	public void shouldBeAbleToInjectBean() throws Exception {
		Assert.assertEquals("halo", greeting.sayHallo());
	}

}
