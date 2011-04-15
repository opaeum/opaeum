package org.tinker;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCompositeComponent extends BaseTest {

	@Test
	public void testCreation() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		SpaceTime spaceTime = new SpaceTime(universe1);
		spaceTime.setName("spaceTime1");
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertEquals("spaceTime1", universe1.getSpaceTime().getName());
		assertNotNull(spaceTime.getUniverse());
		assertEquals("THEGOD", spaceTime.getUniverse().getGod().getName());
		assertNotNull(spaceTime.getSpace());
		assertNotNull(spaceTime.getTime());
		assertNotNull(spaceTime.getSpace().getSpaceTime());
		assertNotNull(spaceTime.getTime().getSpaceTime());
		assertEquals("THEGOD", spaceTime.getSpace().getSpaceTime().getUniverse().getGod().getName());
		assertEquals("THEGOD", spaceTime.getTime().getSpaceTime().getUniverse().getGod().getName());
	}
	
	@Test
	public void testSetToNull() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		SpaceTime spaceTime = new SpaceTime(universe1);
		spaceTime.setName("spaceTime1");
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		spaceTime.setSpace(null);
		assertEquals(5, countVertices());
		assertEquals(3, countEdges());
		assertNull(spaceTime.getSpace());
	}

}
