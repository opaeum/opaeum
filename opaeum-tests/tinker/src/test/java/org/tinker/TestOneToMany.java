package org.tinker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.opeum.test.tinker.BaseLocalDbTest;

public class TestOneToMany extends BaseLocalDbTest {

	@Test
	public void testCompositeCreation() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		assertEquals("THEGOD", universe1.getGod().getName());
		assertEquals("universe1", god.getUniverse().iterator().next().getName());
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		assertEquals("THEGOD", universe2.getGod().getName());
		assertEquals(2, god.getUniverse().size());
		assertEquals(3, countVertices());
		assertEquals(2, countEdges());
	}
	
	@Test
	public void testCompositeDeletion() {
		God god = new God();
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
		assertEquals(1, countVertices());
		assertEquals(0, countEdges());
		god.markDeleted();
	}
	
	@Test
	public void testCompositeDeletionCascading() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		god.markDeleted();
	}
	
	@Test
	public void testCompositalAddAll() {
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
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
	}
	
	@Test
	public void testCompositalAddTo() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe();
		universe1.setName("universe1");
		Universe universe2 = new Universe();
		universe2.setName("universe2");
		god.addToUniverse(universe1);
		god.addToUniverse(universe2);
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
	}
	
	@Test
	public void testCompositalInverseSet() {
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
		
		assertEquals(6, countVertices());
		assertEquals(4, countEdges());

		otherUniverse1.setGod(god);
		assertEquals(6, countVertices());
		assertEquals(4, countEdges());
		assertEquals(3,god.getUniverse().size());
		assertEquals("THEGOD",otherUniverse1.getGod().getName());
		
		otherUniverse2.setGod(god);
		assertEquals(6, countVertices());
		assertEquals(4, countEdges());
		assertEquals(4,god.getUniverse().size());
		assertEquals("THEGOD",otherUniverse2.getGod().getName());
		assertEquals(0, otherGod.getUniverse().size());
	}
	
	@Test
	public void testCompositalSet() {
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
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
		
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
		assertEquals(2, otherGod.getUniverse().size());
		assertEquals("THEOTHERGOD",otherUniverse1.getGod().getName());
		assertEquals("THEOTHERGOD",otherUniverse2.getGod().getName());
		
		god.setUniverse(otherAll);
		assertEquals(0, otherGod.getUniverse().size());
		assertEquals(2, god.getUniverse().size());
		assertEquals(6, countVertices());
		assertEquals(2, countEdges());
		assertNull(universe1.getGod());
		assertNull(universe2.getGod());
	}
	
	@Test
	public void testRemoveAll() {
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
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
		god.removeAllFromUniverse(all);
		assertEquals(0, god.getUniverse().size());
		assertNull(universe1.getGod());
		assertNull(universe2.getGod());
	}	
	
	@Test
	public void testClearAll() {
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
		assertEquals(2, god.getUniverse().size());
		assertEquals("THEGOD",universe1.getGod().getName());
		assertEquals("THEGOD",universe2.getGod().getName());
		god.clearUniverse();
		assertEquals(0, god.getUniverse().size());
		assertNull(universe1.getGod());
		assertNull(universe2.getGod());
	}	
}
