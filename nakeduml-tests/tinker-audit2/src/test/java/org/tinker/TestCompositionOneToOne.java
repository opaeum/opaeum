package org.tinker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestCompositionOneToOne extends BaseLocalDbTest {

	@Test
	public void testCreation() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		SpaceTime spaceTime = new SpaceTime(universe1);
		spaceTime.setName("spaceTime1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertEquals("spaceTime1", universe1.getSpaceTime().getName());
		assertNotNull(spaceTime.getUniverse());
		assertEquals("THEGOD", spaceTime.getUniverse().getGod().getName());
	}
	
	@Test(expected=IllegalStateException.class)
	public void testOneToOneException() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		SpaceTime spaceTime1 = new SpaceTime(universe1);
		spaceTime1.setName("spaceTime1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());
		db.startTransaction();
		SpaceTime spaceTime2 = new SpaceTime(universe1);
		spaceTime2.setName("spaceTime2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(17, countVertices());
		assertEquals(29, countEdges());
	}
	
	@Test
	public void testOneToOneSet() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		SpaceTime spaceTime1 = new SpaceTime(universe1);
		spaceTime1.setName("spaceTime1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());
		db.startTransaction();
		SpaceTime spaceTime2 = new SpaceTime();
		spaceTime2.setName("spaceTime2");
		universe1.setSpaceTime(spaceTime2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(18, countVertices());
		assertEquals(26, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertEquals("spaceTime2", universe1.getSpaceTime().getName());
		assertNull(spaceTime1.getUniverse());
		assertEquals("THEGOD", spaceTime2.getUniverse().getGod().getName());
		db.startTransaction();
		spaceTime1.setUniverse(universe1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(21, countVertices());
		assertEquals(34, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertEquals("spaceTime1", universe1.getSpaceTime().getName());
		assertNull(spaceTime2.getUniverse());
		assertNotNull(spaceTime1.getUniverse());
		assertEquals("THEGOD", spaceTime1.getUniverse().getGod().getName());
		
		db.startTransaction();
		universe1.setSpaceTime(null);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(23, countVertices());
		assertEquals(38, countEdges());
		assertNull(universe1.getSpaceTime());
		assertNull(spaceTime2.getUniverse());
		
		db.startTransaction();
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		universe1.setSpaceTime(spaceTime1);
		universe2.setSpaceTime(spaceTime2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(29, countVertices());
		assertEquals(53, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertNotNull(universe2.getSpaceTime());
		assertEquals("spaceTime1",universe1.getSpaceTime().getName());
		assertEquals("spaceTime2",universe2.getSpaceTime().getName());
		assertNotNull(spaceTime1.getUniverse().getGod());
		assertNotNull(spaceTime2.getUniverse().getGod());
		
		db.startTransaction();
		universe1.setSpaceTime(spaceTime2);
		universe2.setSpaceTime(spaceTime1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(33, countVertices());
		assertEquals(65, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertNotNull(universe2.getSpaceTime());
		assertEquals("spaceTime2",universe1.getSpaceTime().getName());
		assertEquals("spaceTime1",universe2.getSpaceTime().getName());
		assertNotNull(spaceTime1.getUniverse().getGod());
		assertNotNull(spaceTime2.getUniverse().getGod());
		
	}
	
	@Test
	public void testMarkDeleted() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		SpaceTime spaceTime1 = new SpaceTime(universe1);
		spaceTime1.setName("spaceTime1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());
		db.startTransaction();
		universe1.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(21, countEdges());
		assertEquals(0, god.getUniverse().size());
	}	
	
}
