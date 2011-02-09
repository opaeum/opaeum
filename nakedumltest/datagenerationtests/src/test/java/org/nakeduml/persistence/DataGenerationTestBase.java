package org.nakeduml.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import junit.framework.Assert;
import net.sf.nakeduml.seam3.persistence.ManagedHibernateSessionProvider;

import org.hibernate.Session;
import org.jboss.logging.Logger;
import org.jboss.seam.persistence.transaction.DefaultTransaction;
import org.jboss.seam.persistence.transaction.SeamTransaction;
import org.jboss.seam.solder.log.TypedCategory;
import org.junit.Test;

import datagenerationtest.datagenerationtests.nakeduml.Finger;
import datagenerationtest.datagenerationtests.nakeduml.FingerDataGenerator;
import datagenerationtest.datagenerationtests.nakeduml.God;
import datagenerationtest.datagenerationtests.nakeduml.GodDataGenerator;
import datagenerationtest.datagenerationtests.nakeduml.Hand;
import datagenerationtest.datagenerationtests.nakeduml.HandDataGenerator;
import datagenerationtest.datagenerationtests.nakeduml.Ring;
import datagenerationtest.datagenerationtests.nakeduml.RingDataGenerator;
import datagenerationtest.util.FailedConstraintsException;
import datagenerationtest.util.InvariantError;
import datagenerationtest.util.InvariantException;
import datagenerationtest.util.Stdlib;

public class DataGenerationTestBase {

	public static Class<?>[] getTestClasses() {
		return new Class[] { Stdlib.class, FailedConstraintsException.class, InvariantException.class, InvariantError.class, GodDataGenerator.class, HandDataGenerator.class,
				FingerDataGenerator.class, RingDataGenerator.class, StartUpLoadData.class, DataGenerationTestBase.class, God.class, Ring.class, Hand.class, Finger.class,
				ManagedHibernateSessionProvider.class };
	}

	@Inject
	@TypedCategory(DataGenerationTestBase.class)
	Logger log;

	@Inject
	@DefaultTransaction
	SeamTransaction transaction;

	@Inject
	Session session;

	@SuppressWarnings("unchecked")
	@Test
	public void testManagedHibernateSession() throws NotSupportedException, SystemException, SecurityException, IllegalStateException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException {

		Assert.assertNotNull(log);
		
		List<God> gods = session.createQuery("select h from God h").list();
		for (God god : gods) {
			Assert.assertNotNull(god.getName());
			Assert.assertNotSame("",god.getName());
		}
		// The startup creates 3 objects
		Assert.assertEquals(3, gods.size());
		
		List<Ring> rings = new ArrayList<Ring>();
		
		List<Hand> hands = session.createQuery("select h from Hand h").list();
		Assert.assertEquals(9, hands.size());
		for (Hand hand : hands) {
			Assert.assertNotNull(hand.getName());
			Assert.assertNotSame("",hand.getName());
			Assert.assertNotNull(hand.getOther1());
			Assert.assertNotSame("",hand.getOther1());
			for (Finger finger: hand.getFinger()) {
				if (finger.getRing()!=null) {
					rings.add(finger.getRing());
				}
			}
		}
		Assert.assertEquals(9, rings.size());
	}

}
