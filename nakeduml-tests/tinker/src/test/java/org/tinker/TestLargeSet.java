package org.tinker;

import org.junit.Test;
import org.tinker.derivedunion.Hand;
import org.tinker.derivedunion.MamalHand;

public class TestLargeSet extends BaseTest {

	@Test
	public void test() {
		God god = new God();
		for (int i = 0; i < 60000; i++) {
			MamalHand hand = new MamalHand(god);
			hand.setName("hand" + i);
		}
		for (Hand hand : god.getHand()) {
			System.out.println(hand.getName());
		}
	}
	
}
