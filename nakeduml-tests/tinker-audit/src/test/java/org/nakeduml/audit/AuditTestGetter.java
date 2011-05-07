package org.nakeduml.audit;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.audittest.Finger;
import org.audittest.Hand;
import org.audittest.HandAudit;
import org.junit.Test;
import org.tinker.BaseLocalDbTest;

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
		Finger universe1 = new Finger(hand);
		universe1.setName("universe1");
		Finger universe2 = new Finger(hand);
		universe2.setName("universe2");
		Finger universe3 = new Finger(hand);
		universe3.setName("universe3");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(9, countVertices());
		assertEquals(12, countEdges());
		
		assertEquals(2, hand.getAudits().size());
		Iterator<HandAudit> iterator = hand.getAudits().iterator();
		assertEquals(0,iterator.next().getFinger().size());		
		assertEquals(3,iterator.next().getFinger().size());		
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
		assertEquals(13, countVertices());
		assertEquals(19, countEdges());
	}
	
}
