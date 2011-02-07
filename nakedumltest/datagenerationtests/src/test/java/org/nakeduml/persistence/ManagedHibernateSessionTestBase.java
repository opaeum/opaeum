package org.nakeduml.persistence;

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
import datagenerationtest.datagenerationtests.nakeduml.Hand;
import datagenerationtest.datagenerationtests.nakeduml.HandDataGenerator;
import datagenerationtest.util.FailedConstraintsException;
import datagenerationtest.util.InvariantError;
import datagenerationtest.util.InvariantException;
import datagenerationtest.util.Stdlib;

public class ManagedHibernateSessionTestBase {

	public static Class<?>[] getTestClasses() {
		return new Class[] { Stdlib.class, FailedConstraintsException.class, InvariantException.class, InvariantError.class, HandDataGenerator.class, FingerDataGenerator.class, StartUpLoadData.class,
				ManagedHibernateSessionTestBase.class, Hand.class, Finger.class, ManagedHibernateSessionProvider.class };
	}

	@Inject
	@TypedCategory(ManagedHibernateSessionTestBase.class)
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

		log.debug("XXXXXXXXXXXXXXXXXXXXXXXXXX");
		List<Hand> hands = session.createQuery("select h from Hand h").list();
		for (Hand hand : hands) {
			Assert.assertNotNull(hand.getOther1());
			Assert.assertNotSame("",hand.getOther1());
		}
		// The startup creates 3 objects
		Assert.assertEquals(3, hands.size());

		transaction.begin();
		Hand h = new Hand();
		h.setName("first2");
		session.persist(h);
		session.flush();
		transaction.commit();

		transaction.begin();
		h = new Hand();
		h.setName("second2");
		session.persist(h);
		session.flush();
		transaction.rollback();

		session.clear();
		hands = session.createQuery("select h from Hand h").list();
		// The startup creates 3 objects
		Assert.assertEquals(4, hands.size());
	}

}
