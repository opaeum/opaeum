package org.tinker.derivedunion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.tinker.God;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestDerivedUnion extends BaseLocalDbTest {

	@Test
	public void testSettingAndGetting() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		MamalHand mamalHand = new MamalHand(god);
		mamalHand.setName("mamalHand");
		MonkeyHand monkeyHand = new MonkeyHand(god);
		monkeyHand.setName("monkeyHand");
		HumanHand humanHand = new HumanHand(god);
		humanHand.setName("humanHand");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(11, countEdges());
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
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		MamalHand mamalHand = new MamalHand(god);
		mamalHand.setName("mamalHand");
		MonkeyHand monkeyHand = new MonkeyHand(god);
		monkeyHand.setName("monkeyHand");
		HumanHand humanHand = new HumanHand(god);
		humanHand.setName("humanHand");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(11, countEdges());
		db.startTransaction();
		monkeyHand.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(14, countEdges());
	}
	
	@Test
	public void testManyToMany() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Class1 class1 = new Class1(god);
		Class2 class2 = new Class2(god);
		Class3 class3 = new Class3(god);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(11, countEdges());
		db.startTransaction();
		class1.addToClass2(class2);
		class1.addToClass3(class3);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(11, countVertices());
		assertEquals(18, countEdges());
		assertEquals(2, class1.getInterface2().size());
		assertEquals(1, class2.getInterface1().size());
		assertEquals(1, class3.getInterface1().size());
		
	}
}
