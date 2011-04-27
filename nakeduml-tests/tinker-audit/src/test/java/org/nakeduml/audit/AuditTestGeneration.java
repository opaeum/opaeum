package org.nakeduml.audit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.audittest.FatNail;
import org.audittest.Finger;
import org.audittest.Hand;
import org.audittest.HandAudit;
import org.audittest.Nail;
import org.junit.Test;
import org.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class AuditTestGeneration extends BaseLocalDbTest {

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
	}
	
	@Test
	public void testAuditCompositeGodAndUniversesInTwoTransaction() {
		db.startTransaction();
		Hand god = new Hand();
		god.setName("THEGOD");
		db.stopTransaction(Conclusion.SUCCESS);
		db.startTransaction();
		Finger universe1 = new Finger(god);
		universe1.setName("universe1");
		Finger universe2 = new Finger(god);
		universe2.setName("universe2");
		Finger universe3 = new Finger(god);
		universe3.setName("universe3");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(9, countVertices());
		assertEquals(12, countEdges());
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
		assertEquals(12, countEdges());
		db.startTransaction();
		Finger finger4 = new Finger(hand);
		finger4.setName("finger4");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(17, countEdges());
		db.startTransaction();
		finger4.setName("finger41");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(22, countEdges());
		
		assertEquals("finger4", finger4.getAudits().get(1).getPreviousAuditEntry().getName());
		assertEquals("THEHAND", finger4.getAudits().get(1).getPreviousAuditEntry().getHand().getName());
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
	}	
	
}
