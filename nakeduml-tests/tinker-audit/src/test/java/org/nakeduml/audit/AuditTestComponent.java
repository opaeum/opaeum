package org.nakeduml.audit;

import static org.junit.Assert.assertEquals;

import org.audittest.CompositeKing;
import org.audittest.Hand;
import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class AuditTestComponent extends BaseLocalDbTest {

	@Test
	public void testAuditCompositeGodAndUniversesInOneTransaction() {
		db.startTransaction();
		Hand hand = new Hand();
		hand.setName("THEHAND");
		CompositeKing compositeKing1 = new CompositeKing(hand);
		compositeKing1.setName("compositeKing1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(10, countEdges());
	}
	
}
