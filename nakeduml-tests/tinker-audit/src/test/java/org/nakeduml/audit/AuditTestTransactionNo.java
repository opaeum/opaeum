package org.nakeduml.audit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Iterator;

import org.audittest.FatNail;
import org.audittest.Finger;
import org.audittest.FingerAudit;
import org.audittest.Hand;
import org.audittest.HandAudit;
import org.audittest.Nail;
import org.junit.Test;
import org.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class AuditTestTransactionNo extends BaseLocalDbTest {

	@Test
	public void testAuditGod() {
		db.startTransaction();
		Hand hand = new Hand();
		hand.setName("THEHAND");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(1,hand.getAudits().size());
		assertTrue(hand.getAudits().iterator().next() instanceof HandAudit);
		assertEquals("THEHAND",hand.getAudits().iterator().next().getName());
		assertEquals(2, countVertices());
		assertEquals(1, countEdges());		
		assertEquals(2, hand.getAudits().iterator().next().getTransactionNo());
	}
	
	@Test
	public void testAuditCompositeGodAndUniversesInOneTransaction() {
		db.startTransaction();
		Hand hand = new Hand();
		hand.setName("THEGOD");
		Finger universe1 = new Finger(hand);
		universe1.setName("universe1");
		Finger universe2 = new Finger(hand);
		universe2.setName("universe2");
		Finger universe3 = new Finger(hand);
		universe3.setName("universe3");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(10, countEdges());
		assertEquals(2, hand.getAudits().iterator().next().getTransactionNo());
	}
	
	@Test
	public void testAuditCompositeGodAndUniversesInTwoTransaction() {
		db.startTransaction();
		Hand hand = new Hand();
		hand.setName("THEGOD");
		db.stopTransaction(Conclusion.SUCCESS);
		Iterator<HandAudit> iterator1 = hand.getAudits().iterator();
		assertEquals(2, iterator1.next().getTransactionNo());
		assertEquals(2, countVertices());
		assertEquals(1, countEdges());
		db.startTransaction();
		Finger finger1 = new Finger(hand);
		finger1.setName("universe1");
		Finger finger2 = new Finger(hand);
		finger2.setName("universe2");
		Finger finger3 = new Finger(hand);
		finger3.setName("universe3");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(9, countVertices());
		assertEquals(12, countEdges());
		Iterator<HandAudit> iterator2 = hand.getAudits().iterator();
		assertEquals(2, iterator2.next().getTransactionNo());
		assertEquals(3, iterator2.next().getTransactionNo());
		assertEquals(3, finger3.getAudits().iterator().next().getTransactionNo());
	}
	
	@Test
	public void testAuditCompositeGodAndUniversesInThreeTransaction() {
		db.startTransaction();
		Hand hand = new Hand();
		hand.setName("THEHAND");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(2, hand.getAudits().iterator().next().getTransactionNo());
		assertEquals(2, countVertices());
		assertEquals(1, countEdges());		
		db.startTransaction();
		Finger finger1 = new Finger(hand);
		finger1.setName("finger1");
		Finger finger2 = new Finger(hand);
		finger2.setName("finger2");
		Finger finger3 = new Finger(hand);
		finger3.setName("finger3");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(9, countVertices());
		assertEquals(12, countEdges());
		Iterator<HandAudit> iterator1 = hand.getAudits().iterator();
		assertEquals(2, iterator1.next().getTransactionNo());
		assertEquals(3, iterator1.next().getTransactionNo());
		assertEquals(3, finger3.getAudits().iterator().next().getTransactionNo());		
		db.startTransaction();
		Finger finger4 = new Finger(hand);
		finger4.setName("finger4");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(17, countEdges());
		Iterator<HandAudit> iterator2 = hand.getAudits().iterator();
		assertEquals(2, iterator2.next().getTransactionNo());
		assertEquals(3, iterator2.next().getTransactionNo());
		assertEquals(4, finger4.getAudits().iterator().next().getTransactionNo());		
		db.startTransaction();
		finger4.setName("finger41");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(13, countVertices());
		assertEquals(19, countEdges());
		assertEquals("finger4", finger4.getAudits().get(1).getPreviousAuditEntry().getName());
		assertEquals("THEHAND", finger4.getAudits().get(1).getPreviousAuditEntry().getHand().getName());
		Iterator<HandAudit> iterator3 = hand.getAudits().iterator();
		assertEquals(2, iterator3.next().getTransactionNo());
		assertEquals(3, iterator3.next().getTransactionNo());
		assertEquals(4, finger4.getAudits().iterator().next().getTransactionNo());		
	}
	
	@Test
	public void testAuditCompositeHandFingerAndNail() {
		db.startTransaction();
		Hand hand = new Hand();
		hand.setName("THEHAND");
		Finger finger = new Finger(hand);
		finger.setName("finger");
		Nail nail = new FatNail(finger);
		nail.setName("fatnail");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(7, countEdges());		
		assertEquals(2, hand.getAudits().iterator().next().getTransactionNo());		
		assertEquals(2, finger.getAudits().iterator().next().getTransactionNo());		
		assertEquals(2, nail.getAudits().iterator().next().getTransactionNo());		
	}
	
	@Test
	public void testMovingFinger() {
		db.startTransaction();
		Hand hand1 = new Hand();
		hand1.setName("hand1");
		Finger finger1 = new Finger(hand1);
		finger1.setName("finger1");
		Hand hand2 = new Hand();
		hand2.setName("hand2");
		Finger finger2 = new Finger(hand2);
		finger2.setName("finger2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(8, countEdges());		
		assertEquals(2, hand1.getAudits().iterator().next().getTransactionNo());		
		assertEquals(2, finger1.getAudits().iterator().next().getTransactionNo());		
		assertEquals(2, hand2.getAudits().iterator().next().getTransactionNo());		
		assertEquals(2, finger2.getAudits().iterator().next().getTransactionNo());		
		db.startTransaction();
		finger1.setHand(hand2);
		finger2.setHand(hand1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(18, countEdges());		
		Iterator<HandAudit> hand1Iter = hand1.getAudits().iterator();
		assertEquals(2, hand1Iter.next().getTransactionNo());		
		assertEquals(3, hand1Iter.next().getTransactionNo());		
		Iterator<HandAudit> hand2Iter = hand2.getAudits().iterator();
		assertEquals(2, hand2Iter.next().getTransactionNo());		
		assertEquals(3, hand2Iter.next().getTransactionNo());		
		Iterator<FingerAudit> finger1Iter = finger1.getAudits().iterator();
		assertEquals(2, finger1Iter.next().getTransactionNo());		
		assertEquals(3, finger1Iter.next().getTransactionNo());		
		Iterator<FingerAudit> finger2Iter = finger2.getAudits().iterator();
		assertEquals(2, finger2Iter.next().getTransactionNo());		
		assertEquals(3, finger2Iter.next().getTransactionNo());		
	}
	
	@Test
	public void testMovingFingerTwice() {
		db.startTransaction();
		Hand hand1 = new Hand();
		hand1.setName("hand1");
		Finger finger1 = new Finger(hand1);
		finger1.setName("finger1");
		Hand hand2 = new Hand();
		hand2.setName("hand2");
		Finger finger2 = new Finger(hand2);
		finger2.setName("finger2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(8, countEdges());	
		
		assertEquals(2, hand1.getAudits().iterator().next().getTransactionNo());		
		assertEquals(2, finger1.getAudits().iterator().next().getTransactionNo());		
		assertEquals(2, hand2.getAudits().iterator().next().getTransactionNo());		
		assertEquals(2, finger2.getAudits().iterator().next().getTransactionNo());	
		
		db.startTransaction();
		finger1.setHand(hand2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(11, countVertices());
		assertEquals(15, countEdges());
		assertEquals(2, hand2.getFinger().size());
		assertEquals(0, hand1.getFinger().size());
		
		Iterator<HandAudit> hand1Iter = hand1.getAudits().iterator();
		assertEquals(2, hand1Iter.next().getTransactionNo());		
		assertEquals(3, hand1Iter.next().getTransactionNo());		
		Iterator<HandAudit> hand2Iter = hand2.getAudits().iterator();
		assertEquals(2, hand2Iter.next().getTransactionNo());		
		assertEquals(3, hand2Iter.next().getTransactionNo());		
		Iterator<FingerAudit> finger1Iter = finger1.getAudits().iterator();
		assertEquals(2, finger1Iter.next().getTransactionNo());		
		assertEquals(3, finger1Iter.next().getTransactionNo());		
		Iterator<FingerAudit> finger2Iter = finger2.getAudits().iterator();
		assertEquals(2, finger2Iter.next().getTransactionNo());		
		assertFalse(finger2Iter.hasNext());		
		
	}
	
}
