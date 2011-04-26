package org.tinker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestCompositionOneToOne extends BaseTest {

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
	}
	
	@Test(expected=IllegalStateException.class)
	public void testOneToOneException() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		SpaceTime spaceTime1 = new SpaceTime(universe1);
		spaceTime1.setName("spaceTime1");
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		SpaceTime spaceTime2 = new SpaceTime(universe1);
		spaceTime2.setName("spaceTime2");
	}
	
	@Test
	public void testOneToOneSet() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		SpaceTime spaceTime1 = new SpaceTime(universe1);
		spaceTime1.setName("spaceTime1");
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		SpaceTime spaceTime2 = new SpaceTime();
		spaceTime2.setName("spaceTime2");
		universe1.setSpaceTime(spaceTime2);
		assertEquals(6, countVertices());
		assertEquals(4, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertEquals("spaceTime2", universe1.getSpaceTime().getName());
		assertNull(spaceTime1.getUniverse());
		assertEquals("THEGOD", spaceTime2.getUniverse().getGod().getName());
		spaceTime1.setUniverse(universe1);
		assertEquals(6, countVertices());
		assertEquals(4, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertEquals("spaceTime1", universe1.getSpaceTime().getName());
		assertNull(spaceTime2.getUniverse());
		assertNotNull(spaceTime1.getUniverse());
		assertEquals("THEGOD", spaceTime1.getUniverse().getGod().getName());
		
		universe1.setSpaceTime(null);
		assertEquals(6, countVertices());
		assertEquals(3, countEdges());
		assertNull(universe1.getSpaceTime());
		assertNull(spaceTime2.getUniverse());
		
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		universe1.setSpaceTime(spaceTime1);
		universe2.setSpaceTime(spaceTime2);
		assertEquals(7, countVertices());
		assertEquals(6, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertNotNull(universe2.getSpaceTime());
		assertEquals("spaceTime1",universe1.getSpaceTime().getName());
		assertEquals("spaceTime2",universe2.getSpaceTime().getName());
		assertNotNull(spaceTime1.getUniverse().getGod());
		assertNotNull(spaceTime2.getUniverse().getGod());
		
		universe1.setSpaceTime(spaceTime2);
		universe2.setSpaceTime(spaceTime1);
		assertEquals(7, countVertices());
		assertEquals(6, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertNotNull(universe2.getSpaceTime());
		assertEquals("spaceTime2",universe1.getSpaceTime().getName());
		assertEquals("spaceTime1",universe2.getSpaceTime().getName());
		assertNotNull(spaceTime1.getUniverse().getGod());
		assertNotNull(spaceTime2.getUniverse().getGod());
		
	}
	
	@Test
	public void testMarkDeleted() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		SpaceTime spaceTime1 = new SpaceTime(universe1);
		spaceTime1.setName("spaceTime1");
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		universe1.markDeleted();
		assertEquals(1, countVertices());
		assertEquals(0, countEdges());
	}	
	
}
