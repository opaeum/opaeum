package org.tinker.interfacetest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.tinker.BaseLocalDbTest;
import org.tinker.God;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestInterfacesCompositionalOneToMany extends BaseLocalDbTest {

	@Test
	public void testSettingAndGetting() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Creature creature1 = new Creature(god);
		creature1.setName("creature1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(4, countVertices());
		assertEquals(4, countEdges());
		assertEquals("creature1", god.getBeing().iterator().next().getName());
		assertEquals("THEGOD", creature1.getGod().getName());
		db.startTransaction();
		Human human = new Human(god);
		human.setName("human");
		Alien alien = new Alien(god);
		alien.setName("alien");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(9, countVertices());
		assertEquals(11, countEdges());
		int countBeing = 0;
		int countCreature = 0;
		int countHuman = 0;
		int countAlien = 0;
		for (Being being : god.getBeing()) {
			if (being instanceof Being) {
				countBeing++;
			}
			if (being instanceof Creature) {
				countCreature++;
			}
			if (being instanceof Human) {
				countHuman++;
			}
			if (being instanceof Alien) {
				countAlien++;
			}
		}
		assertEquals(3, countBeing);
		assertEquals(3, countCreature);
		assertEquals(1, countHuman);
		assertEquals(1, countAlien);
	}
	
	@Test
	public void testMarkDeleted() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Creature creature1 = new Creature(god);
		creature1.setName("creature1");
		Human human = new Human(god);
		human.setName("human");
		Alien alien = new Alien(god);
		alien.setName("alien");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(10, countEdges());
		db.startTransaction();
		god.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(14, countEdges());
	}	
}
