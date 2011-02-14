package org.nakeduml.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.hibernate.Session;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.seam.persistence.transaction.DefaultTransaction;
import org.jboss.seam.persistence.transaction.SeamTransaction;
import org.jboss.seam.solder.log.TypedCategory;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nakeduml.arquillian.ArquillianUtils;
import org.nakeduml.arquillian.ArtifactNames;
import org.nakeduml.arquillian.MavenArtifactResolver;

import datagenerationtest.org.nakeduml.Finger;
import datagenerationtest.org.nakeduml.God;
import datagenerationtest.org.nakeduml.Hand;
import datagenerationtest.org.nakeduml.Ring;


@RunWith(Arquillian.class)
public class HandFingerRingTest extends BaseTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		WebArchive war = ArquillianUtils.createWarArchive(true);
		war.addWebResource("hibernate.cfg.xml", "classes/hibernate.cfg.xml");
		war.addWebResource("data.generation.properties", "data.generation.properties");
		war.addLibraries(MavenArtifactResolver.resolve(ArtifactNames.NAKED_UML_UTIL));
		war.addClasses(getTestClasses());
		return war;
	}

	@Inject
	@TypedCategory(HandFingerRingTest.class)
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
		Assert.assertEquals(3, gods.size());

		List<Ring> rings = new ArrayList<Ring>();
		List<Hand> hands = session.createQuery("select h from Hand h").list();
		Assert.assertEquals(9, hands.size());
		for (Hand hand : hands) {
			Assert.assertNotNull(hand.getName());
			Assert.assertNotSame("", hand.getName());
			for (Finger finger : hand.getFinger()) {
				if (finger.getRing() != null) {
					rings.add(finger.getRing());
				}
			}
		}
		Assert.assertEquals(9, rings.size());
		
	}

}
