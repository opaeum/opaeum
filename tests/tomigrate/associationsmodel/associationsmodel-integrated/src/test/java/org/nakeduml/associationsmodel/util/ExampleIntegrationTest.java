package org.opaeum.associationsmodel.util;

import assocationsmodel.root.Root;

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

@RunWith(Arquillian.class)
public class ExampleIntegrationTest {
	@Inject
	private Session session;


	@Deployment
	static public Archive<?> createTestArchive() throws IllegalArgumentException, IOException, ClassNotFoundException {
		return TestUtil.createTestArchive();
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void test() {
		List<Root> roots = session.createQuery("select h from Root h").list();
		Assert.assertFalse(roots.size()>0);
	}

}