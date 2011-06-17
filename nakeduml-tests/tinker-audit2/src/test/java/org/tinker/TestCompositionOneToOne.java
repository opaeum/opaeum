package org.tinker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;

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
	
	@Test(expected=RuntimeException.class)
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
		Universe universe2 = new Universe(god);
		spaceTime1.setUniverse(universe2);
		
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(21, countVertices());
		assertEquals(30, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertEquals("spaceTime2", universe1.getSpaceTime().getName());
		assertEquals("THEGOD", spaceTime2.getUniverse().getGod().getName());
		db.startTransaction();
		spaceTime1.setUniverse(universe1);
		spaceTime2.setUniverse(universe2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(25, countVertices());
		assertEquals(38, countEdges());
		assertNotNull(universe1.getSpaceTime());
		assertEquals("spaceTime1", universe1.getSpaceTime().getName());
		assertNotNull(spaceTime1.getUniverse());
		assertEquals("THEGOD", spaceTime1.getUniverse().getGod().getName());
		
		db.startTransaction();
		universe1.setSpaceTime(null);
		db.stopTransaction(Conclusion.SUCCESS);
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
		assertEquals(15, countVertices());
		assertEquals(22, countEdges());
		assertEquals(0, god.getUniverse().size());
	}	
	
}
