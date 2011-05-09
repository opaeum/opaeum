package org.tinker;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;
import org.tinker.derivedunion.Hand;
import org.tinker.derivedunion.MamalHand;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestLargeSet extends BaseLocalDbTest {

//	@Test
	public void test() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		db.startTransaction();
		God god = new God();
		for (int i = 0; i < 100000; i++) {
			MamalHand hand = new MamalHand(god);
			hand.setName("hand" + i);
		}
		db.stopTransaction(Conclusion.SUCCESS);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		stopWatch.reset();
		stopWatch.start();
		db.startTransaction();
		for (Hand hand : god.getHand()) {
//			System.out.println(hand.getName());
		}
		db.stopTransaction(Conclusion.SUCCESS);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
	}
	
}
