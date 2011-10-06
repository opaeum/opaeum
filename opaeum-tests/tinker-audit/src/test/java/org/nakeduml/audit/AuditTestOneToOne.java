package org.opaeum.audit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.audittest.Glove;
import org.audittest.GloveAudit;
import org.audittest.Hand;
import org.audittest.HandAudit;
import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class AuditTestOneToOne extends BaseLocalDbTest {

	@Test
	public void testHandGlove() {
		db.startTransaction();
		Hand hand = new Hand();
		hand.setName("hand1");
		Glove glove = new Glove(hand);
		glove.setName("glove1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(4, countVertices());
		assertEquals(4, countEdges());
		assertTrue(hand.getAudits().iterator().hasNext());
		GloveAudit gloveAudit = hand.getAudits().iterator().next().getGlove();
		assertNotNull(gloveAudit.getHand());
		assertEquals("hand1",gloveAudit.getHand().getName());
	}
	
	@Test
	public void testOrientCountEdgesBug() {
		db.startTransaction();
		Hand hand = new Hand();
		hand.setName("hand1");
		Glove glove = new Glove(hand);
		glove.setName("glove1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(4, countVertices());
		assertEquals(4, countEdges());
		db.startTransaction();
		glove.setName("glove11");
		hand.setName("hand11");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(6, countEdges());
	}
	
	@Test
	public void testHandGlove2Transactions() {
		db.startTransaction();
		Hand hand = new Hand();
		hand.setName("hand1");
		Glove glove = new Glove(hand);
		glove.setName("glove1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(4, countVertices());
		assertEquals(4, countEdges());
		db.startTransaction();
		glove.setName("glove11");
		hand.setName("hand11");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(6, countEdges());
		
		List<HandAudit> handAudits = hand.getAudits();
		assertEquals(2, handAudits.size());
		HandAudit handAudit1 = handAudits.get(0);
		HandAudit handAudit2 = handAudits.get(1);
		db.startTransaction();
		assertEquals(handAudit2.getPreviousAuditEntry().getName(), handAudit1.getName());
		assertEquals("glove11", handAudit2.getGlove().getName());
		assertEquals("glove1", handAudit1.getGlove().getName());
		
		List<GloveAudit> gloveAudits = glove.getAudits();
		assertEquals(2, gloveAudits.size());
		GloveAudit gloveAudit1 = gloveAudits.get(0);
		GloveAudit gloveAudit2 = gloveAudits.get(1);
		assertEquals(gloveAudit2.getPreviousAuditEntry().getName(), gloveAudit1.getName());
		assertEquals("hand1", gloveAudit1.getHand().getName());
		assertEquals("glove1", gloveAudit2.getPreviousAuditEntry().getName());
		assertEquals("hand1", gloveAudit2.getPreviousAuditEntry().getHand().getName());
		assertEquals("hand11", gloveAudit2.getPreviousAuditEntry().getHand().getOriginal().getName());
		assertEquals("glove11", handAudit2.getGlove().getName());
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
	@Test
	public void testHandGloveSetter() {
		db.startTransaction();
		Hand hand1 = new Hand();
		hand1.setName("hand1");
		Glove glove1 = new Glove(hand1);
		glove1.setName("glove1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(4, countVertices());
		assertEquals(4, countEdges());
		assertTrue(hand1.getAudits().iterator().hasNext());
		GloveAudit gloveAudit = hand1.getAudits().iterator().next().getGlove();
		assertNotNull(gloveAudit.getHand());
		assertEquals("hand1",gloveAudit.getHand().getName());
		db.startTransaction();
		Hand hand2 = new Hand();
		hand2.setName("hand2");
		Glove glove2 = new Glove(hand2);
		glove2.setName("glove2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(8, countEdges());
		db.startTransaction();
		hand1.setGlove(glove2);
		glove1.setHand(new Hand());
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(17, countEdges());
		db.startTransaction();
		hand2.setGlove(glove1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(17, countVertices());
		assertEquals(22, countEdges());
	}	
}
