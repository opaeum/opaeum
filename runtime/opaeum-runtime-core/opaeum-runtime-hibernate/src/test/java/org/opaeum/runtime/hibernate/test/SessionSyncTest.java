package org.opaeum.runtime.hibernate.test;

import java.util.Collection;
import java.util.Map;

import junit.framework.TestCase;

import org.opaeum.opaeum_hibernate_tests.util.Opaeum_hibernate_testsEnvironment;
import org.opaeum.runtime.domain.IPersistentObject;
import org.opaeum.runtime.persistence.ConversationalPersistence;
import org.opaeum.runtime.persistence.event.ChangedEntity;
import org.opaeum.test.hibernate.Hand;

public class SessionSyncTest extends TestCase{
	public void testOverwriteDb(){
		ConversationalPersistence p = Opaeum_hibernate_testsEnvironment.INSTANCE.createConversationalPersistence();
		Hand hand = new Hand();
		p.persist(hand);
		p.flush();
		ConversationalPersistence p2 = Opaeum_hibernate_testsEnvironment.INSTANCE.createConversationalPersistence();
		Hand hand2 = p2.find(Hand.class, hand.getId());
		hand.setName("asdf");
		p.flush();
		assertNull(hand2.getName());
		hand2.setName("2345");
		Map<ChangedEntity,IPersistentObject> cf = p2.synchronizeWithDatabaseAndFindConflicts();
		assertEquals(1, cf.size());
		assertSame(hand2, cf.values().iterator().next());
		p2.overwriteDatabaseWithConflicts(cf);
		p2.flush();
		ConversationalPersistence p3=Opaeum_hibernate_testsEnvironment.INSTANCE.createConversationalPersistence();
		Hand hand3 = p3.find(Hand.class, hand.getId());
		assertEquals(hand2.getName(), hand3.getName());
	}
	public void testReloadStaleObjects(){
		ConversationalPersistence p = Opaeum_hibernate_testsEnvironment.INSTANCE.createConversationalPersistence();
		Hand hand = new Hand();
		p.persist(hand);
		p.flush();
		ConversationalPersistence p2 = Opaeum_hibernate_testsEnvironment.INSTANCE.createConversationalPersistence();
		Hand hand2 = p2.find(Hand.class, hand.getId());
		hand.setName("asdf");
		p.flush();
		assertNull(hand2.getName());
		hand2.setName("2345");
		Collection<IPersistentObject> reloadStaleObjects = p2.reloadStaleObjects();
		assertTrue(reloadStaleObjects.contains(hand2));
		assertEquals(hand.getName(), hand2.getName());
	}
	public void testOverwriteConflicts(){
		ConversationalPersistence p = Opaeum_hibernate_testsEnvironment.INSTANCE.createConversationalPersistence();
		Hand hand = new Hand();
		p.persist(hand);
		p.flush();
		ConversationalPersistence p2 = Opaeum_hibernate_testsEnvironment.INSTANCE.createConversationalPersistence();
		Hand hand2 = p2.find(Hand.class, hand.getId());
		hand.setName("asdf");
		p.flush();
		assertNull(hand2.getName());
		hand2.setName("2345");
		Map<ChangedEntity,IPersistentObject> cf = p2.synchronizeWithDatabaseAndFindConflicts();
		assertEquals(1, cf.size());
		assertSame(hand2, cf.values().iterator().next());
		p2.overwriteConflictsFromDatabase(cf);
		p2.flush();
		ConversationalPersistence p3=Opaeum_hibernate_testsEnvironment.INSTANCE.createConversationalPersistence();
		Hand hand3 = p3.find(Hand.class, hand.getId());
		assertEquals(hand3.getName(), hand.getName());
//		p2.reloadStaleObjects();
//		assertEquals(hand2.getName(), hand.getName());
//		System.out.println(cf);
	}

}
