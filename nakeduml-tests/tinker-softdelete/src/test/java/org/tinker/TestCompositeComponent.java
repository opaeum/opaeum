package org.tinker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

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
	
	@Test
	public void testBug() {
		Vertex one = org.util.GraphDb.getDB().addVertex(null);
		Vertex two = org.util.GraphDb.getDB().addVertex(null);
		Edge edge = org.util.GraphDb.getDB().addEdge(null, one, two,"one-two");
		edge.setProperty("inClass", two.getClass().getName());
		
		assertEquals(2, countVertices());
		assertEquals(1, countEdges());		
		
		Iterable<Edge> iter8 = one.getOutEdges("one-two");
		if ( iter8.iterator().hasNext() ) {
			org.util.GraphDb.getDB().removeEdge(iter8.iterator().next());
		}
		assertEquals(2, countVertices());
		assertEquals(0, countEdges());		
		
		Iterable<Edge> iter9 = one.getOutEdges("one-two");
		assertFalse(iter9.iterator().hasNext());
	}

}
