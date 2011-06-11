package org.nakeduml.audit;

import static org.junit.Assert.assertEquals;

import org.audittest.Four;
import org.audittest.Hand;
import org.audittest.One;
import org.audittest.Three;
import org.audittest.Two;
import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class AuditTestOneToManyToMany extends BaseLocalDbTest {

	@Test
	public void testAuditGod() {
		db.startTransaction();
		Hand hand = new Hand();
		hand.setName("THEHAND");
		One one1 = new One(hand);
		one1.setName("one1");
		Two two1 = new Two(one1);
		two1.setName("two1");
		Three three1 = new Three(two1);
		three1.setName("three1");
		Four four1 = new Four(three1);
		four1.setName("four1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());
		db.startTransaction();
		Three three2 = new Three(two1);
		three2.setName("three2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(13, countVertices());
		assertEquals(17, countEdges());
	}
	
}
