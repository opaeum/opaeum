package org.nakeduml.jbpm.test;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import jbpm.jbpm.TheBoss;
import jbpm.jbpm.TheBossDataGenerator;
import net.sf.nakeduml.arquillian.ArquillianUtils;
import net.sf.nakeduml.test.NakedUtilTestClasses;

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

@RunWith(Arquillian.class)
public class JbpmTest extends BaseTest {

	@Deployment
	public static Archive<?> createTestArchive() throws IllegalArgumentException, ClassNotFoundException, IOException {
		WebArchive war = ArquillianUtils.createWarArchive(true);
		war.addWebResource("hibernate.cfg.xml", "classes/hibernate.cfg.xml");
		war.addWebResource("data.generation.properties", "data.generation.properties");
		war.addClasses(getTestClasses());
		war.addClasses(NakedUtilTestClasses.getTestClasses()); 
		return war;
	}

	@Inject
	@DefaultTransaction
	SeamTransaction transaction;

	@Inject
	Session session;
	
	@Inject
	TheBossDataGenerator theBossDataGenerator;

	@SuppressWarnings("unchecked")
	@Test
	public void testDataGeneration() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException {
		List<TheBoss> theBosses = session.createQuery("select h from TheBoss h").list();
		theBossDataGenerator.exportTheBoss(theBosses);	
		Assert.assertEquals(3, theBosses.size());
	}

}
