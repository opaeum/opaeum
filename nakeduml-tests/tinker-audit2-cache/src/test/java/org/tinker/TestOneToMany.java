package org.tinker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestOneToMany extends BaseLocalDbTest {

	@Test
	public void testCompositeCreation() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(4, countVertices());
		assertEquals(4, countEdges());
		assertEquals("THEGOD", universe1.getGod().getName());
		assertEquals("universe1", god.getUniverse().iterator().next().getName());
		db.startTransaction();
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(7, countVertices());
		assertEquals(8, countEdges());
		assertEquals("THEGOD", universe2.getGod().getName());
		assertEquals(2, god.getUniverse().size());
	}
	
	@Test
	public void testCompositeDeletion() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(2, countVertices());
		assertEquals(1, countEdges());
		db.startTransaction();
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(5, countVertices());
		assertEquals(5, countEdges());
		db.startTransaction();
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(9, countEdges());
		assertEquals("THEGOD", universe1.getGod().getName());
		assertEquals("THEGOD", universe2.getGod().getName());
		assertEquals(2, god.getUniverse().size());
		db.startTransaction();
		universe1.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(12, countEdges());
		assertEquals(1, god.getUniverse().size());
		db.startTransaction();
		universe2.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(15, countEdges());
		assertEquals(0, god.getUniverse().size());
		db.startTransaction();
		god.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(13, countVertices());
		assertEquals(16, countEdges());
	}
	
	@Test
	public void testCompositeDeletionCascading() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		god.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(7, countEdges());	
	}
	
	@Test
	public void testCompositalAddAll() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe();
		universe1.setName("universe1");
		Universe universe2 = new Universe();
		universe2.setName("universe2");
		Set<Universe> all = new HashSet<Universe>();
		all.add(universe1);
		all.add(universe2);
		god.addAllToUniverse(all);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(7, countEdges());			
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
	}
	
	@Test
	public void testCompositalAddTo() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe();
		universe1.setName("universe1");
		Universe universe2 = new Universe();
		universe2.setName("universe2");
		god.addToUniverse(universe1);
		god.addToUniverse(universe2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(7, countEdges());			
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
	}
	
	@Test
	public void testCompositalInverseSet() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe();
		universe1.setName("universe1");
		Universe universe2 = new Universe();
		universe2.setName("universe2");
		god.addToUniverse(universe1);
		god.addToUniverse(universe2);
		God otherGod = new God();
		otherGod.setName("THEOTHERGOD");
		Universe otherUniverse1 = new Universe();
		otherUniverse1.setName("otherUniverse1");
		Universe otherUniverse2 = new Universe();
		otherUniverse2.setName("otherUniverse2");
		otherGod.addToUniverse(otherUniverse1);
		otherGod.addToUniverse(otherUniverse2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(14, countEdges());			
		db.startTransaction();
		otherUniverse1.setGod(god);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(15, countVertices());
		assertEquals(19, countEdges());
		assertEquals(3,god.getUniverse().size());
		assertEquals("THEGOD",otherUniverse1.getGod().getName());
		db.startTransaction();
		otherUniverse2.setGod(god);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(18, countVertices());
		assertEquals(24, countEdges());
		assertEquals(4,god.getUniverse().size());
		assertEquals("THEGOD",otherUniverse2.getGod().getName());
		assertEquals(0, otherGod.getUniverse().size());
	}
	
	@Test
	public void testCompositalSet() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe();
		universe1.setName("universe1");
		Universe universe2 = new Universe();
		universe2.setName("universe2");
		Set<Universe> all = new HashSet<Universe>();
		all.add(universe1);
		all.add(universe2);
		god.addAllToUniverse(all);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(7, countEdges());
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
		
		db.startTransaction();
		God otherGod = new God();
		otherGod.setName("THEOTHERGOD");
		Universe otherUniverse1 = new Universe();
		otherUniverse1.setName("otherUniverse1");
		Universe otherUniverse2 = new Universe();
		otherUniverse2.setName("otherUniverse2");
		Set<Universe> otherAll = new HashSet<Universe>();
		otherAll.add(otherUniverse1);
		otherAll.add(otherUniverse2);
		otherGod.addAllToUniverse(otherAll);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(14, countEdges());		
		assertEquals(2, otherGod.getUniverse().size());
		assertEquals("THEOTHERGOD",otherUniverse1.getGod().getName());
		assertEquals("THEOTHERGOD",otherUniverse2.getGod().getName());
		db.startTransaction();
		god.setUniverse(otherAll);
		otherGod.setUniverse(all);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(18, countVertices());
		assertEquals(28, countEdges());			
		assertEquals(2, otherGod.getUniverse().size());
		assertEquals(2, god.getUniverse().size());
		assertNotNull(universe1.getGod());
		assertNotNull(universe2.getGod());
	}
	
	@Test
	public void testRemoveAll() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe();
		universe1.setName("universe1");
		Universe universe2 = new Universe();
		universe2.setName("universe2");
		Set<Universe> all = new HashSet<Universe>();
		all.add(universe1);
		all.add(universe2);
		god.addAllToUniverse(all);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(7, countEdges());		
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
		db.startTransaction();
		god.removeAllFromUniverse(all);
		God otherGod = new God();
		otherGod.addAllToUniverse(all);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(11, countVertices());
		assertEquals(15, countEdges());		
		assertEquals(0, god.getUniverse().size());
		assertNotNull(universe1.getGod());
		assertNotNull(universe2.getGod());
	}	
	
	@Test
	public void testClearAll() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe();
		universe1.setName("universe1");
		Universe universe2 = new Universe();
		universe2.setName("universe2");
		Set<Universe> all = new HashSet<Universe>();
		all.add(universe1);
		all.add(universe2);
		god.addAllToUniverse(all);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(7, countEdges());			
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
		db.startTransaction();
		god.clearUniverse();
		God otherGod = new God();
		otherGod.addAllToUniverse(all);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(11, countVertices());
		assertEquals(15, countEdges());		
		assertEquals(0, god.getUniverse().size());
		assertNotNull(universe1.getGod());
		assertNotNull(universe2.getGod());
	}	
}
