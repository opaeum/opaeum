package org.tinker.interfacetest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.tinker.God;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestDtoInterfaced extends BaseLocalDbTest {
	
	@Test
	public void testSettingAndGetting() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Creature creature = new Creature(god);
		creature.setName("creature");
		Human human = new Human(god);
		human.setName("human");
		Alien alien = new Alien(god);
		alien.setName("alien");
		Spook spook = new Spook(god);
		spook.setName("spook");
		Soul soul = new Soul(god);
		soul.setName("soul");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(17, countEdges());
		db.startTransaction();
		creature.setSpirit(spook);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(21, countEdges());
		db.startTransaction();
		soul.setBeing(human);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(16, countVertices());
		assertEquals(25, countEdges());
		db.startTransaction();
		human.setSpirit(spook);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(20, countVertices());
		assertEquals(31, countEdges());
		assertEquals("spook", human.getSpirit().getName()); 
		BeingController beingController = new BeingController();
		beingController.setDb(db);
		BeingDto beingDto = beingController.getBeingForSpirit(spook.getId());
		assertEquals("human", beingDto.getName());
		SpiritController spiritController = new SpiritController();
		spiritController.setDb(db);
		SpiritDto spiritDto = spiritController.getSpiritForBeing(human.getId());
		assertEquals("spook", spiritDto.getName());
	}
	
}
