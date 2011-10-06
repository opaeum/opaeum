package org.opaeum.audit;

import static org.junit.Assert.assertEquals;

import org.audittest.Finger;
import org.audittest.Hand;
import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class AuditTestAddAndSettingInOneTransaction extends BaseLocalDbTest {

	@Test
	public void testAuditCompositeGodAndUniversesInOneTransaction() {
		db.startTransaction();
		Hand hand = new Hand();
		hand.setName("THEHAND");
		Finger finger1 = new Finger(hand);
		finger1.setName("finger1");
		finger1.setHand(hand); 
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(4, countVertices());
		assertEquals(4, countEdges());
	}
	
}
