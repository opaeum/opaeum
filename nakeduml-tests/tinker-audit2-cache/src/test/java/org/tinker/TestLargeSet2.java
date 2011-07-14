package org.tinker;

import org.apache.commons.lang.time.StopWatch;
import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestLargeSet2 extends BaseLocalDbTest {

	@Test
	public void test() {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		db.startTransaction();
		God god = new God(true);
		god.setName("didthiswork");
		for (int i = 0; i < 5000; i++) {
			if (i % 100 == 0) {
				System.out.println(i);
			}
			Universe universe1 = new Universe(god);
			universe1.setName("universe" + i);
			BlackHole blackHole1 = new BlackHole(universe1);
			blackHole1.setName("blackHole1");
			BlackHole blackHole2 = new BlackHole(universe1);
			blackHole2.setName("blackHole2");
			BlackHole blackHole3 = new BlackHole(universe1);
			blackHole3.setName("blackHole3");
		}
		db.stopTransaction(Conclusion.SUCCESS);
		stopWatch.stop();
	}
	
}
