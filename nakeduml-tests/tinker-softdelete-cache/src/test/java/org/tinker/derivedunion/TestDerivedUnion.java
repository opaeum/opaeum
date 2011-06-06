package org.tinker.derivedunion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.tinker.BaseTest;
import org.tinker.God;

public class TestDerivedUnion extends BaseTest {

	@Test
	public void testSettingAndGetting() {
		God god = new God();
		god.setName("THEGOD");
		MamalHand mamalHand = new MamalHand(god);
		mamalHand.setName("mamalHand");
		MonkeyHand monkeyHand = new MonkeyHand(god);
		monkeyHand.setName("monkeyHand");
		HumanHand humanHand = new HumanHand(god);
		humanHand.setName("humanHand");
		assertEquals(4, countVertices());
		assertEquals(3, countEdges());
		assertEquals(3, god.getHand().size());
		assertEquals(1, god.getMonkeyHand().size());
		assertEquals(1, god.getMamalHand().size());
		assertEquals(1, god.getHumanHand().size());
		assertNotNull(mamalHand.getGod());
		assertNotNull(humanHand.getGod());
		assertNotNull(monkeyHand.getGod());
	}
	
	@Test
	public void testMarkDeleted() {
		God god = new God();
		god.setName("THEGOD");
		MamalHand mamalHand = new MamalHand(god);
		mamalHand.setName("mamalHand");
		MonkeyHand monkeyHand = new MonkeyHand(god);
		monkeyHand.setName("monkeyHand");
		HumanHand humanHand = new HumanHand(god);
		humanHand.setName("humanHand");
		assertEquals(4, countVertices());
		assertEquals(3, countEdges());
		assertEquals(3, god.getHand().size());
		assertEquals(1, god.getMonkeyHand().size());
		monkeyHand.markDeleted();
		assertEquals(4, countVertices());
		assertEquals(3, countEdges());
		assertEquals(2, god.getHand().size());
		assertEquals(0, god.getMonkeyHand().size());
	}
	
	@Test
	public void testManyToMany() {
		God god = new God();
		god.setName("THEGOD");
		
		Class1 class1 = new Class1(god);
		Class2 class2 = new Class2(god);
		Class3 class3 = new Class3(god);
		
		assertEquals(4, countVertices());
		assertEquals(3, countEdges());
		
		class1.addToClass2(class2);
		class1.addToClass3(class3);
		assertEquals(4, countVertices());
		assertEquals(5, countEdges());
		assertEquals(2, class1.getInterface2().size());
		assertEquals(1, class2.getInterface1().size());
		assertEquals(1, class3.getInterface1().size());
		
	}
}
