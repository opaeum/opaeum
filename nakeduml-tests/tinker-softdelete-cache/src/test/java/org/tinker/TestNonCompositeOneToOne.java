package org.tinker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;

public class TestNonCompositeOneToOne extends BaseLocalDbTest {

	@Test
	public void testSettingAndGetting() {
		God god = new God();
		god.setName("THEGOD");
		Angel angel1 = new Angel(god);
		angel1.setName("angel1");
		Angel angel2 = new Angel(god);
		angel2.setName("angel2");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		universe1.setAngel(angel1);
		assertEquals(5, countVertices());
		assertEquals(5, countEdges());
		assertNotNull(universe1.getAngel());
		assertEquals("angel1", universe1.getAngel().getName());
		assertNull(universe2.getAngel());
		assertNotNull(angel1.getUniverse());
		assertNull(angel2.getUniverse());
		angel2.setUniverse(universe2);
		assertEquals(5, countVertices());
		assertEquals(6, countEdges());
		assertNotNull(angel2.getUniverse());
		assertNotNull(angel1.getUniverse());
		assertEquals("universe2", angel2.getUniverse().getName());
		
		universe1.setAngel(angel2);
		assertEquals(5, countVertices());
		assertEquals(5, countEdges());
		assertNull(universe2.getAngel());
		assertNull(angel1.getUniverse());
		angel1.setUniverse(universe2);
		assertEquals(5, countVertices());
		assertEquals(6, countEdges());
		assertNotNull(angel2.getUniverse());
		assertNotNull(angel1.getUniverse());
		assertEquals("universe1", angel2.getUniverse().getName());
		assertEquals("universe2", angel1.getUniverse().getName());
		assertEquals("angel1", universe2.getAngel().getName());
		assertEquals("angel2", universe1.getAngel().getName());
	}
	
	@Test
	public void testMarkDeleted() {
		God god = new God();
		god.setName("THEGOD");
		Angel angel1 = new Angel(god);
		angel1.setName("angel1");
		Angel angel2 = new Angel(god);
		angel2.setName("angel2");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		universe1.setAngel(angel1);
		assertEquals(5, countVertices());
		assertEquals(5, countEdges());
		assertNotNull(universe1.getAngel());
		assertEquals("angel1", universe1.getAngel().getName());
		assertNull(universe2.getAngel());
		assertNotNull(angel1.getUniverse());
		assertNull(angel2.getUniverse());
		angel2.setUniverse(universe2);
		assertEquals(5, countVertices());
		assertEquals(6, countEdges());
		assertNotNull(angel2.getUniverse());
		assertNotNull(angel1.getUniverse());
		assertEquals("universe2", angel2.getUniverse().getName());

		universe1.markDeleted();
		assertEquals(5, countVertices());
		assertEquals(6, countEdges());
		assertNull(angel1.getUniverse());
		assertNotNull(angel2.getUniverse());
		
		angel2.markDeleted();
		assertEquals(5, countVertices());
		assertEquals(6, countEdges());
		assertNull(universe2.getAngel());
	}	
}
