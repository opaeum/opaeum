package org.nakeduml.audit;

import org.audittest.Finger;
import org.audittest.Hand;
import org.junit.Test;
import org.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class AuditRecordHookTest extends BaseLocalDbTest {

	@Test
	public void test() {
		db.startTransaction();
		Hand hand = new Hand();
		hand.setName("hand1");
		Finger finger = new Finger(hand);
		finger.setName("finger1");
		db.stopTransaction(Conclusion.SUCCESS);
	}
}
