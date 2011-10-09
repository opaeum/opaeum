package org.opaeum.persistence;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import junit.framework.Assert;
import net.sf.opaeum.arquillian.ArquillianUtils;
import net.sf.opaeum.test.NakedUtilTestClasses;

import org.hibernate.Session;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.seam.persistence.transaction.DefaultTransaction;
import org.jboss.seam.persistence.transaction.SeamTransaction;
import org.jboss.seam.solder.log.TypedCategory;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import datagenerationtest.org.opaeum.Canvas;
import datagenerationtest.org.opaeum.Component;
import datagenerationtest.org.opaeum.Folder;
import datagenerationtest.org.opaeum.God;
import datagenerationtest.org.opaeum.GodFolder;
import datagenerationtest.org.opaeum.Subject;
import datagenerationtest.org.opaeum.SubjectFolder;
import datagenerationtest.org.opaeum.UI;

@RunWith(Arquillian.class)
public class HierarchyDataGenerationTest extends BaseTest {

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
	@TypedCategory(HierarchyDataGenerationTest.class)
	Logger log;

	@Inject
	@DefaultTransaction
	SeamTransaction transaction;

	@Inject
	Session session;

	@SuppressWarnings("unchecked")
	@Test
	public void testDataGeneration() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException {

		Assert.assertNotNull(log);

		List<God> gods = session.createQuery("select h from God h").list();
		for (God god : gods) {
			Assert.assertNotNull(god.getName());
			Assert.assertNotSame("", god.getName());
		}
		// The startup creates 3 objects
		Assert.assertEquals(2, gods.size());

		List<GodFolder> godFolders = session.createQuery("select h from GodFolder h").list();
		Assert.assertEquals(4, godFolders.size());

		List<Subject> subjects = session.createQuery("select h from Subject h").list();
		Assert.assertEquals(4, subjects.size());

		List<SubjectFolder> subjectFolders = session.createQuery("select h from SubjectFolder h").list();
		Assert.assertEquals(8, subjectFolders.size());

		List<UI> uis = session.createQuery("select h from UI h").list();
		Assert.assertEquals(16, uis.size());

		List<Folder> folders = session.createQuery("select h from Folder h").list();
		Assert.assertEquals(24, folders.size());
		
		List<Canvas> canvass = session.createQuery("select h from Canvas h").list();
		Assert.assertEquals(48, canvass.size());
		
		List<Component> components = session.createQuery("select h from Component h").list();
		Assert.assertEquals(128, components.size());
		String previous = "";
		for (Component component : components) {
			Assert.assertNotSame(component.getName(), previous);
			Assert.assertNotNull(component.getName());
			Assert.assertNotSame("", component.getName());
			previous = component.getName();
		}

	}

}
