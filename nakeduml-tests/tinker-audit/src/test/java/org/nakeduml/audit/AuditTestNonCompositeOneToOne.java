package org.nakeduml.audit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.audittest.Hand;
import org.audittest.HandAudit;
import org.audittest.One;
import org.audittest.OneAudit;
import org.audittest.OtherOne;
import org.audittest.OtherOneAudit;
import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class AuditTestNonCompositeOneToOne extends BaseLocalDbTest {

	@Test
	public void testNonCompositeOneToOne() {
		db.startTransaction();
		Hand hand1 = new Hand();
		hand1.setName("hand1");
		db.stopTransaction(Conclusion.SUCCESS);
		List<HandAudit> handAudits = hand1.getAudits();
		assertEquals(2, countVertices());
		assertEquals(1, countEdges());
		assertEquals(1, handAudits.size());
		assertEquals(0, handAudits.get(0).getOne().size());
		db.startTransaction();
		One one1 = new One(hand1);
		one1.setName("one1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(5, countVertices());
		assertEquals(5, countEdges());
		handAudits = hand1.getAudits();
		List<OneAudit> oneAudits = one1.getAudits();
		assertEquals(2, handAudits.size());
		assertEquals(1, oneAudits.size());
		db.startTransaction();
		OtherOne otherOne1 = new OtherOne(hand1);
		otherOne1.setName("otherOne1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(9, countEdges());
		handAudits = hand1.getAudits();
		oneAudits = one1.getAudits();
		List<OtherOneAudit> otherOneAudits = otherOne1.getAudits();
		assertEquals(3, handAudits.size());
		assertEquals(1, oneAudits.size());
		assertEquals(1, otherOneAudits.size());
		db.startTransaction();
		one1.setOtherOne(otherOne1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());
		handAudits = hand1.getAudits();
		oneAudits = one1.getAudits();
		otherOneAudits = otherOne1.getAudits();
		assertEquals(3, handAudits.size());
		assertEquals(2, oneAudits.size());
		assertEquals(2, otherOneAudits.size());
		db.startTransaction();
		one1.setName("one1Again");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(11, countVertices());
		assertEquals(14, countEdges());
		handAudits = hand1.getAudits();
		oneAudits = one1.getAudits();
		otherOneAudits = otherOne1.getAudits();
		assertEquals(3, handAudits.size());
		assertEquals(3, oneAudits.size());
		assertEquals(2, otherOneAudits.size());
		db.startTransaction();
		OtherOne otherOne2 = new OtherOne(hand1);
		otherOne2.setName("otherOne2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(18, countEdges());
		handAudits = hand1.getAudits();
		oneAudits = one1.getAudits();
		otherOneAudits = otherOne1.getAudits();
		List<OtherOneAudit> otherOne2Audits = otherOne2.getAudits();
		assertEquals(4, handAudits.size());
		assertEquals(3, oneAudits.size());
		assertEquals(2, otherOneAudits.size());
		assertEquals(1, otherOne2Audits.size());
		db.startTransaction();
		one1.setOtherOne(otherOne2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(17, countVertices());
		assertEquals(23, countEdges());
		handAudits = hand1.getAudits();
		oneAudits = one1.getAudits();
		otherOneAudits = otherOne1.getAudits();
		otherOne2Audits = otherOne2.getAudits();
		assertEquals(4, handAudits.size());
		assertEquals(4, oneAudits.size());
		assertEquals(3, otherOneAudits.size());
		assertEquals(2, otherOne2Audits.size());
		db.startTransaction();
		hand1.removeFromOtherOne(otherOne1);
		Hand hand2 = new Hand();
		otherOne1.setHand(hand2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(21, countVertices());
		assertEquals(28, countEdges());
		handAudits = hand1.getAudits();
		oneAudits = one1.getAudits();
		otherOneAudits = otherOne1.getAudits();
		otherOne2Audits = otherOne2.getAudits();
		assertEquals(5, handAudits.size());
		assertEquals(4, oneAudits.size());
		assertEquals(4, otherOneAudits.size());
		assertEquals(2, otherOne2Audits.size());
		assertNotNull(oneAudits.get(3).getOtherOne());
		db.startTransaction();
		assertNull(otherOneAudits.get(2).getOne());
		assertNull(otherOneAudits.get(3).getOne());
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
	@Test
	public void testRemovalOfEdge() {
		db.startTransaction();
		Hand hand1 = new Hand();
		hand1.setName("hand1");
		One one1 = new One(hand1);
		one1.setName("one1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(4, countVertices());
		assertEquals(4, countEdges());
		db.startTransaction();
		one1.setHand(new Hand());
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(9, countEdges());
		db.startTransaction();
		hand1.setName("hand1Again");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(9, countVertices());
		assertEquals(10, countEdges());
		db.startTransaction();
		assertEquals(0, hand1.getAudits().get(2).getOne().size());
		db.stopTransaction(Conclusion.SUCCESS);
		
	}
	
}
