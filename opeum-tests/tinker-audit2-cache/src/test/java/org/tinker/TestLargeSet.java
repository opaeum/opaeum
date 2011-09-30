package org.tinker;

import java.util.Set;

import org.apache.commons.lang.time.StopWatch;
import org.opeum.test.tinker.BaseLocalDbTest;
import org.tinker.derivedunion.Hand;
import org.tinker.derivedunion.MamalHand;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestLargeSet extends BaseLocalDbTest {

//	@Test
	public void test() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
//		OGlobalConfiguration.TX_USE_LOG.setValue(false);
//		GraphDb.getRawGraph().declareIntent( new OIntentMassiveInsert() );
		db.startTransaction();
		God god = new God();
		for (int i = 0; i < 100000; i++) {
			MamalHand hand = new MamalHand(god);
			hand.setName("hand" + i);
			if (i % 200 == 0) {
				System.out.println(i);
			}
		}
		db.stopTransaction(Conclusion.SUCCESS);
//		GraphDb.getRawGraph().declareIntent( null );
		stopWatch.stop();
		System.out.println(stopWatch.toString());

		stopWatch.reset();
		stopWatch.start();
		db.startTransaction();
		int i = 0;
		Set<Hand> hand2 = god.getHand();
		System.out.println(hand2.size());
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		stopWatch.reset();
		stopWatch.start();
		for (Hand hand : hand2) {
			if (i % 200 == 0) {
				System.out.println(hand.getName() + " " + i);
			}
			i++;
		}
		db.stopTransaction(Conclusion.SUCCESS);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
	}
	
	
//	@Test
	public void test2() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		db.startTransaction();
		God god = new God();
		for (int i = 0; i < 100000; i++) {
			MamalHand hand = new MamalHand(god);
			hand.setName("hand" + i);
			if (i % 200 == 0) {
				System.out.println(i);
			}
		}
		db.stopTransaction(Conclusion.SUCCESS);
		stopWatch.stop();
		System.out.println(stopWatch.toString());

		stopWatch.reset();
		stopWatch.start();
		db.startTransaction();
		Set<Hand> hand2 = god.getHand();
		System.out.println(hand2.size());
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		stopWatch.reset();
		stopWatch.start();
		int j = 0;
		for (Hand hand : hand2) {
			hand.setName("hand" + j++);
			if (j % 200 == 0) {
				System.out.println(hand.getName());
			}
		}
		db.stopTransaction(Conclusion.SUCCESS);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		
		stopWatch.reset();
		stopWatch.start();
		db.startTransaction();
		hand2 = god.getHand();
		System.out.println(hand2.size());
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		stopWatch.reset();
		stopWatch.start();
		int i = 0;
		for (Hand hand : hand2) {
			if (i % 200 == 0) {
				System.out.println(i);
			}
			i++;
		}
		db.stopTransaction(Conclusion.SUCCESS);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
		
		stopWatch.reset();
		stopWatch.start();
		db.startTransaction();
		System.out.println("settings god name");
		god.setName("god2");
		db.stopTransaction(Conclusion.SUCCESS);
		stopWatch.stop();
		System.out.println(stopWatch.toString());
	}	
	
}
