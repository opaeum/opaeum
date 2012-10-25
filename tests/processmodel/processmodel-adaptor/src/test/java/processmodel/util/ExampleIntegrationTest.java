package processmodel.util;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import processmodel.processes.ProcessOwner;

@RunWith(Arquillian.class)
public class ExampleIntegrationTest {
	@Inject
	private Session session;


	@Deployment
	static public Archive<?> createTestArchive() throws IllegalArgumentException, IOException, ClassNotFoundException {
		return NakedUmlTestUtil.createTestArchive();
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void test() {
		List<ProcessOwner> roots = session.createQuery("select h from ProcessOwner h").list();
		Assert.assertFalse(roots.size()>0);
	}

}