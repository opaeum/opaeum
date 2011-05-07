package org.nakeduml.audit;

import static org.junit.Assert.assertEquals;

import org.audittest.Hand;
import org.audittest.One;
import org.audittest.OtherOne;
import org.junit.Test;
import org.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class AuditTestNonCompositeOneToOne extends BaseLocalDbTest {

	@Test
	public void testNonCompositeOneToOne() {
		db.startTransaction();
		Hand hand1 = new Hand();
		hand1.setName("hand1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(2, countVertices());
		assertEquals(1, countEdges());
		db.startTransaction();
		One one1 = new One(hand1);
		one1.setName("one1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(5, countVertices());
		assertEquals(6, countEdges());
		db.startTransaction();
		OtherOne otherOne1 = new OtherOne(hand1);
		otherOne1.setName("otherOne1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(11, countEdges());
		db.startTransaction();
		one1.setOtherOne(otherOne1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(17, countEdges());
		db.startTransaction();
		one1.setName("one1Again");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(11, countVertices());
		assertEquals(19, countEdges());
		db.startTransaction();
		OtherOne otherOne2 = new OtherOne(hand1);
		otherOne2.setName("otherOne2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(24, countEdges());
		db.startTransaction();
		one1.setOtherOne(otherOne2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(17, countVertices());
		assertEquals(31, countEdges());
		
	}
	
}
