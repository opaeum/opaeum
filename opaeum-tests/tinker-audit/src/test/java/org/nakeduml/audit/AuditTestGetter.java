package org.opaeum.audit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.audittest.Finger;
import org.audittest.FingerAudit;
import org.audittest.Hand;
import org.audittest.HandAudit;
import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class AuditTestGetter extends BaseLocalDbTest {

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
		assertEquals(1, hand.getAudits().size());
		assertEquals(3,hand.getAudits().iterator().next().getFinger().size());
	}
	
	@Test
	public void testAuditCompositeGodAndUniversesInTwoTransaction() {
		db.startTransaction();
		Hand hand = new Hand();
		hand.setName("THEGOD");
		db.stopTransaction(Conclusion.SUCCESS);
		db.startTransaction();
		Finger finger1 = new Finger(hand);
		finger1.setName("universe1");
		Finger finger2 = new Finger(hand);
		finger2.setName("universe2");
		Finger finger3 = new Finger(hand);
		finger3.setName("universe3");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(9, countVertices());
		assertEquals(11, countEdges());
		
		assertEquals(2, hand.getAudits().size());
		Iterator<HandAudit> iterator = hand.getAudits().iterator();
		db.startTransaction();
		assertEquals(0,iterator.next().getFinger().size());		
		assertEquals(3,iterator.next().getFinger().size());
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
	@Test
	public void testAuditCompositeGodAndUniversesInThreeTransaction() {
		db.startTransaction();
		Hand hand = new Hand();
		hand.setName("THEHAND");
		db.stopTransaction(Conclusion.SUCCESS);
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
		assertEquals(11, countEdges());
		db.startTransaction();
		Finger finger4 = new Finger(hand);
		finger4.setName("finger4");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(15, countEdges());
		db.startTransaction();
		finger4.setName("finger41");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(13, countVertices());
		assertEquals(16, countEdges());
	}
	
	@Test
	public void testHandFingerMoving() {
		db.startTransaction();
		Hand hand1 = new Hand();
		hand1.setName("hand1");
		Finger finger1 = new Finger(hand1);
		finger1.setName("finger1");
		Finger finger2 = new Finger(hand1);
		finger2.setName("finger2");
		Finger finger3 = new Finger(hand1);
		finger3.setName("finger3");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(10, countEdges());
		db.startTransaction();
		finger1.setName("finger1Again1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(9, countVertices());
		assertEquals(11, countEdges());
		db.startTransaction();
		finger1.setName("finger1Again2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(12, countEdges());
		db.startTransaction();
		Hand hand2 = new Hand();
		hand2.setName("hand2");
		Finger finger21 = new Finger(hand2);
		finger21.setName("finger21");
		Finger finger22 = new Finger(hand2);
		finger22.setName("finger22");
		Finger finger23 = new Finger(hand2);
		finger23.setName("finger23");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(18, countVertices());
		assertEquals(22, countEdges());
		db.startTransaction();
		finger21.setName("finger21Again1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(19, countVertices());
		assertEquals(23, countEdges());
		db.startTransaction();
		finger21.setName("finger21Again2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(20, countVertices());
		assertEquals(24, countEdges());
		db.startTransaction();
		finger21.setHand(hand1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(23, countVertices());
		assertEquals(29, countEdges());
		
		List<HandAudit> hand1Audits = hand1.getAudits();
		assertEquals(2, hand1Audits.size());
		List<HandAudit> hand2Audits = hand2.getAudits();
		assertEquals(2, hand2Audits.size());
		
		HandAudit hand1Audit = hand1Audits.get(0);
		db.startTransaction();
		Set<FingerAudit> finger1Audits = hand1Audit.getFinger();
		assertEquals(3, finger1Audits.size());
		boolean foundFinger1Again2 = false;
		for (FingerAudit fingerAudit : finger1Audits) {
			if (fingerAudit.getName().equals("finger1Again2")) {
				foundFinger1Again2 = true;
				break;
			}
		}
		assertTrue(foundFinger1Again2);
		hand1Audit = hand1Audits.get(1);
		finger1Audits = hand1Audit.getFinger();
		assertEquals(4, finger1Audits.size());
		Set<FingerAudit> finger2Audits = hand2Audits.get(0).getFinger();
		assertEquals(3, finger2Audits.size());
		finger2Audits = hand2Audits.get(1).getFinger();
		assertEquals(2, finger2Audits.size());
		db.stopTransaction(Conclusion.SUCCESS);
		db.startTransaction();
		finger22.setName("finger22Again");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(24, countVertices());
		assertEquals(37, countEdges());
		db.startTransaction();
		hand2Audits = hand2.getAudits();
		assertEquals(2, hand2Audits.size());
		boolean finger21Name = false;
		boolean finger22Name = false;
		boolean finger23Name = false;
		finger2Audits = hand2Audits.get(0).getFinger();
		for (FingerAudit fingerHand2Audit : finger2Audits) {
			if (fingerHand2Audit.getName().equals("finger21Again2")) {
				finger21Name = true;
			} else if (fingerHand2Audit.getName().equals("finger22")) {
				finger22Name = true;
			} else if (fingerHand2Audit.getName().equals("finger23")) {
				finger23Name = true;
			} else {
				fail();
			}
		}
		db.stopTransaction(Conclusion.SUCCESS);
		assertTrue(finger21Name);
		assertTrue(finger22Name);
		assertTrue(finger23Name);

		
	}
	
}
