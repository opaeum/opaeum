package org.tinker;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCompositionLaOneToOne extends BaseTest {

	@Test
	public void testCreation() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		SpaceTime spaceTime = new SpaceTime(universe1);
		spaceTime.setName("spaceTime1");
		assertEquals(3, countVertices());
		assertEquals(2, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertEquals("spaceTime1", universe1.getSpaceTime().getName());
		assertNotNull(spaceTime.getUniverse());
		assertEquals("THEGOD", spaceTime.getUniverse().getGod().getName());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testOneToOneException() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		SpaceTime spaceTime1 = new SpaceTime(universe1);
		spaceTime1.setName("spaceTime1");
		assertEquals(3, countVertices());
		assertEquals(2, countEdges());
		SpaceTime spaceTime2 = new SpaceTime(universe1);
		spaceTime2.setName("spaceTime2");
	}
	
	public void testOneToOneSet() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		SpaceTime spaceTime1 = new SpaceTime(universe1);
		spaceTime1.setName("spaceTime1");
		assertEquals(3, countVertices());
		assertEquals(2, countEdges());
		SpaceTime spaceTime2 = new SpaceTime();
		spaceTime2.setName("spaceTime2");
		universe1.setSpaceTime(spaceTime2);
		assertEquals(4, countVertices());
		assertEquals(2, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertEquals("spaceTime2", universe1.getSpaceTime().getName());
		assertNull(spaceTime1.getUniverse());
		assertEquals("THEGOD", spaceTime2.getUniverse().getGod().getName());
	}
	
}
