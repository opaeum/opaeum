package org.tinker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.neo4j.graphdb.TransactionFailureException;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestOneToMany extends BaseLocalDbTest {

	@Test
	public void testCompositeCreation() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		assertEquals("THEGOD", universe1.getGod().getName());
		assertEquals("universe1", god.getUniverse().iterator().next().getName());
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals("THEGOD", universe2.getGod().getName());
		assertEquals(2, god.getUniverse().size());
		assertEquals(3, countVertices());
		assertEquals(3, countEdges());
	}
	
	@Test
	public void testCompositeDeletion() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		assertEquals(1, countVertices());
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		assertEquals(2, countVertices());
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		assertEquals(3, countVertices());
		assertEquals("THEGOD", universe1.getGod().getName());
		assertEquals("THEGOD", universe2.getGod().getName());
		assertEquals(2, god.getUniverse().size());
		universe1.markDeleted();
		assertEquals(1, god.getUniverse().size());
		universe2.markDeleted();
		assertEquals(0, god.getUniverse().size());
		assertEquals(3, countVertices());
		assertEquals(3, countEdges());
		god.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
	@Test
	public void testCompositeDeletionCascading() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		god.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
	@Test
	public void testCompositalAddAll() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(true);
		universe1.setName("universe1");
		Universe universe2 = new Universe(true);
		universe2.setName("universe2");
		Set<Universe> all = new HashSet<Universe>();
		all.add(universe1);
		all.add(universe2);
		god.addAllToUniverse(all);
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
	@Test
	public void testCompositalAddTo() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(true);
		universe1.setName("universe1");
		Universe universe2 = new Universe(true);
		universe2.setName("universe2");
		god.addToUniverse(universe1);
		god.addToUniverse(universe2);
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
	@Test
	public void testCompositalInverseSet() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(true);
		universe1.setName("universe1");
		Universe universe2 = new Universe(true);
		universe2.setName("universe2");
		god.addToUniverse(universe1);
		god.addToUniverse(universe2);
		assertEquals(3, countEdges());

		God otherGod = new God(true);
		otherGod.setName("THEOTHERGOD");
		Universe otherUniverse1 = new Universe(true);
		otherUniverse1.setName("otherUniverse1");
		Universe otherUniverse2 = new Universe(true);
		otherUniverse2.setName("otherUniverse2");
		otherGod.addToUniverse(otherUniverse1);
		otherGod.addToUniverse(otherUniverse2);
		
		assertEquals(6, countVertices());
		assertEquals(6, countEdges());
		db.stopTransaction(Conclusion.SUCCESS);

		db.startTransaction();
		otherUniverse1.setGod(god);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(6, countEdges());
		assertEquals(3,god.getUniverse().size());
		assertEquals("THEGOD",otherUniverse1.getGod().getName());
		
		db.startTransaction();
		otherUniverse2.setGod(god);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(6, countEdges());
		assertEquals(4,god.getUniverse().size());
		assertEquals("THEGOD",otherUniverse2.getGod().getName());
		assertEquals(0, otherGod.getUniverse().size());
	}
	
	@Test(expected=TransactionFailureException.class)
	public void testCompositalSet() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(true);
		universe1.setName("universe1");
		Universe universe2 = new Universe(true);
		universe2.setName("universe2");
		Set<Universe> all = new HashSet<Universe>();
		all.add(universe1);
		all.add(universe2);
		god.addAllToUniverse(all);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
		assertEquals(3, countEdges());
		
		db.startTransaction();
		God otherGod = new God(true);
		otherGod.setName("THEOTHERGOD");
		Universe otherUniverse1 = new Universe(true);
		otherUniverse1.setName("otherUniverse1");
		Universe otherUniverse2 = new Universe(true);
		otherUniverse2.setName("otherUniverse2");
		Set<Universe> otherAll = new HashSet<Universe>();
		otherAll.add(otherUniverse1);
		otherAll.add(otherUniverse2);
		otherGod.addAllToUniverse(otherAll);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(2, otherGod.getUniverse().size());
		assertEquals("THEOTHERGOD",otherUniverse1.getGod().getName());
		assertEquals("THEOTHERGOD",otherUniverse2.getGod().getName());
		assertEquals(6, countEdges());
		
		db.startTransaction();
		god.setUniverse(otherAll);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(0, otherGod.getUniverse().size());
		assertEquals(2, god.getUniverse().size());
		assertEquals(6, countVertices());
		assertEquals(2, countEdges());
		assertNull(universe1.getGod());
		assertNull(universe2.getGod());
		db.stopTransaction(Conclusion.SUCCESS);
	}
	
	@Test(expected=TransactionFailureException.class)
	public void testRemoveAll() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(true);
		universe1.setName("universe1");
		Universe universe2 = new Universe(true);
		universe2.setName("universe2");
		Set<Universe> all = new HashSet<Universe>();
		all.add(universe1);
		all.add(universe2);
		god.addAllToUniverse(all);
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
		god.removeAllFromUniverse(all);
		assertEquals(0, god.getUniverse().size());
		assertNull(universe1.getGod());
		assertNull(universe2.getGod());
		db.stopTransaction(Conclusion.SUCCESS);
	}	
	
	@Test(expected=TransactionFailureException.class)
	public void testClearAll() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(true);
		universe1.setName("universe1");
		Universe universe2 = new Universe(true);
		universe2.setName("universe2");
		Set<Universe> all = new HashSet<Universe>();
		all.add(universe1);
		all.add(universe2);
		god.addAllToUniverse(all);
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
		god.clearUniverse();
		assertEquals(0, god.getUniverse().size());
		assertNull(universe1.getGod());
		assertNull(universe2.getGod());
		db.stopTransaction(Conclusion.SUCCESS);
	}	
}
