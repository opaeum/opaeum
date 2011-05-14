package org.tinker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.orientechnologies.orient.core.exception.OTransactionException;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestCompositeComponent extends BaseLocalDbTest {

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
		assertNotNull(spaceTime.getSpace());
		assertNotNull(spaceTime.getTime());
		assertNotNull(spaceTime.getSpace().getSpaceTime());
		assertNotNull(spaceTime.getTime().getSpaceTime());
		assertEquals("THEGOD", spaceTime.getSpace().getSpaceTime().getUniverse().getGod().getName());
		assertEquals("THEGOD", spaceTime.getTime().getSpaceTime().getUniverse().getGod().getName());
	}
	
	@Test(expected=OTransactionException.class)
	public void testSetToNull() {
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
		db.startTransaction();
		spaceTime.setSpace(null);
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
}
