package org.tinker.interfacetest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.tinker.BaseTest;
import org.tinker.God;

public class TestInterfacesCompositionalOneToMany extends BaseTest {

	@Test
	public void testSettingAndGetting() {
		God god = new God();
		god.setName("THEGOD");
		Creature creature1 = new Creature(god);
		creature1.setName("creature1");
		assertEquals(2, countVertices());
		assertEquals(1, countEdges());
		assertEquals("creature1", god.getBeing().iterator().next().getName());
		assertEquals("THEGOD", creature1.getGod().getName());
		Human human = new Human(god);
		human.setName("human");
		Alien alien = new Alien(god);
		alien.setName("alien");
		assertEquals(4, countVertices());
		assertEquals(3, countEdges());
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
		God god = new God();
		god.setName("THEGOD");
		Creature creature1 = new Creature(god);
		creature1.setName("creature1");
		Human human = new Human(god);
		human.setName("human");
		Alien alien = new Alien(god);
		alien.setName("alien");
		assertEquals(4, countVertices());
		assertEquals(3, countEdges());
		god.markDeleted();
		assertEquals(0, countVertices());
		assertEquals(0, countEdges());
	}	
}
