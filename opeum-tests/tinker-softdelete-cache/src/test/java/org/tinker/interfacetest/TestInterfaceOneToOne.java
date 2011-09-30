package org.tinker.interfacetest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.opeum.test.tinker.BaseLocalDbTest;
import org.tinker.God;

public class TestInterfaceOneToOne extends BaseLocalDbTest {

	@Test
	public void testSettingAndGetting() {
		God god = new God();
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
		assertEquals(6, countVertices());
		assertEquals(5, countEdges());
		creature.setSpirit(spook);
		assertEquals(6, countVertices());
		assertEquals(6, countEdges());
		soul.setBeing(human);
		assertEquals(6, countVertices());
		assertEquals(7, countEdges());
		human.setSpirit(spook);
		assertEquals(6, countVertices());
		assertEquals(6, countEdges());
		assertEquals("spook", human.getSpirit().getName()); 
	}
	
	@Test
	public void testMarkDeleted() {
		God god = new God();
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
		assertEquals(6, countVertices());
		assertEquals(5, countEdges());
		assertEquals(3, god.getBeing().size());
		creature.setSpirit(spook);
		assertEquals(6, countVertices());
		assertEquals(6, countEdges());
		soul.setBeing(human);
		assertEquals(6, countVertices());
		assertEquals(7, countEdges());
		human.setSpirit(spook);
		assertEquals(6, countVertices());
		assertEquals(6, countEdges());
		assertEquals("spook", human.getSpirit().getName()); 
		
		assertEquals(3, god.getBeing().size());
		assertNotNull(spook.getBeing());
		human.markDeleted();
		assertEquals(6, countVertices());
		assertEquals(6, countEdges());
		assertEquals(2, god.getBeing().size());
		assertNull(spook.getBeing());
	}	
}
