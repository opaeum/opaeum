package org.opeum.persistence;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import net.sf.opeum.arquillian.ArquillianUtils;
import net.sf.opeum.test.NakedUtilTestClasses;

import org.hibernate.Session;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.seam.persistence.transaction.DefaultTransaction;
import org.jboss.seam.persistence.transaction.SeamTransaction;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import datagenerationtest.org.opeum.Cell;
import datagenerationtest.org.opeum.FakeWorkspace;
import datagenerationtest.org.opeum.God;
import datagenerationtest.org.opeum.RealWorkspace;
import datagenerationtest.org.opeum.WorkspaceElement;


@RunWith(Arquillian.class)
public class InterfaceTest extends BaseTest {

	@Deployment
	public static Archive<?> createTestArchive() throws IllegalArgumentException, ClassNotFoundException, IOException {
		WebArchive war = ArquillianUtils.createWarArchive(true);
		war.addWebResource("hibernate.cfg.xml", "classes/hibernate.cfg.xml");
		war.addWebResource("data.generation.properties", "data.generation.properties");
		war.addClasses(NakedUtilTestClasses.getTestClasses()); 
		war.addClasses(getTestClasses());
		return war;
	}

	@Inject
	@DefaultTransaction
	SeamTransaction transaction;

	@Inject
	Session session;

	@SuppressWarnings("unchecked")
	@Test
	public void testDataGeneration() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException {

		List<God> gods = session.createQuery("select h from God h").list();
		for (God god : gods) {
			Assert.assertNotNull(god.getName());
			Assert.assertNotSame("", god.getName());
		}
		Assert.assertEquals(2, gods.size());
		
		List<RealWorkspace> realWorkspaces = session.createQuery("select h from RealWorkspace h").list();
		Assert.assertEquals(4, realWorkspaces.size());
		List<FakeWorkspace> fakeWorkspaces = session.createQuery("select h from FakeWorkspace h").list();
		Assert.assertEquals(4, fakeWorkspaces.size());
		List<WorkspaceElement> workspaceElements = session.createQuery("select h from WorkspaceElement h").list();
		Assert.assertEquals(16, workspaceElements.size());
		List<Cell> cells = session.createQuery("select h from Cell h").list();
		Assert.assertEquals(16, cells.size());

//		List<Many1> many1s = session.createQuery("select h from Many1 h").list();
//		Assert.assertEquals(162, many1s.size());
//		List<Many2> many2s = session.createQuery("select h from Many2 h").list();
//		Assert.assertEquals(162, many2s.size());

	}

}
