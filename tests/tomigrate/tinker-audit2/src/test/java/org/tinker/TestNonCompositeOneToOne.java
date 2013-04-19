package org.tinker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestNonCompositeOneToOne extends BaseLocalDbTest{

	@Test
	public void testSettingAndGetting() {
		db.startTransaction();
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
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());
		db.startTransaction();
		universe1.setAngel(angel1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(17, countEdges());
		assertNotNull(universe1.getAngel());
		assertEquals("angel1", universe1.getAngel().getName());
		assertNull(universe2.getAngel());
		assertNotNull(angel1.getUniverse());
		assertNull(angel2.getUniverse());
		db.startTransaction();
		angel2.setUniverse(universe2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(21, countEdges());
		assertNotNull(angel2.getUniverse());
		assertNotNull(angel1.getUniverse());
		assertEquals("universe2", angel2.getUniverse().getName());
		db.startTransaction();
		universe1.setAngel(angel2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(18, countVertices());
		assertEquals(27, countEdges());
		assertNull(universe2.getAngel());
		assertNull(angel1.getUniverse());
		db.startTransaction();
		angel1.setUniverse(universe2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(20, countVertices());
		assertEquals(31, countEdges());
		assertNotNull(angel2.getUniverse());
		assertNotNull(angel1.getUniverse());
		assertEquals("universe1", angel2.getUniverse().getName());
		assertEquals("universe2", angel1.getUniverse().getName());
		assertEquals("angel1", universe2.getAngel().getName());
		assertEquals("angel2", universe1.getAngel().getName());
	}
	
	@Test
	public void testMarkDeleted() {
		db.startTransaction();
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
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());
		db.startTransaction();
		universe1.setAngel(angel1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(17, countEdges());
		assertNotNull(universe1.getAngel());
		assertEquals("angel1", universe1.getAngel().getName());
		assertNull(universe2.getAngel());
		assertNotNull(angel1.getUniverse());
		assertNull(angel2.getUniverse());
		db.startTransaction();
		angel2.setUniverse(universe2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(21, countEdges());
		assertNotNull(angel2.getUniverse());
		assertNotNull(angel1.getUniverse());
		assertEquals("universe2", angel2.getUniverse().getName());
		db.startTransaction();
		universe1.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(17, countVertices());
		assertEquals(26, countEdges());
		assertNull(angel1.getUniverse());
		assertNotNull(angel2.getUniverse());
		db.startTransaction();
		angel2.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(20, countVertices());
		assertEquals(31, countEdges());
		assertNull(universe2.getAngel());
		
		db.startTransaction();
		assertEquals(3, god.getAudits().size());
		assertEquals(2, god.getAudits().get(0).getAngels().size());
		assertEquals(2, god.getAudits().get(0).getUniverse().size());
		assertEquals(2, god.getAudits().get(1).getAngels().size());
		assertEquals(1, god.getAudits().get(1).getUniverse().size());
		assertEquals(1, god.getAudits().get(2).getAngels().size());
		assertEquals(1, god.getAudits().get(2).getUniverse().size());
		db.stopTransaction(Conclusion.SUCCESS);
	}	
}
