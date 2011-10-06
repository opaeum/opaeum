package org.tinker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.opeum.test.tinker.BaseLocalDbTest;

public class TestNonCompositeOneToMany extends BaseLocalDbTest {

	@Test
	public void testSettingAndGetting() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		Demon demon1 = new Demon(god);
		demon1.setName("demon1");
		Demon demon2 = new Demon(god);
		demon2.setName("demon2");
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		universe1.addToDemon(demon1);
		assertEquals(5, countVertices());
		assertEquals(5, countEdges());
		assertEquals(1, universe1.getDemon().size());
		universe1.addToDemon(demon2);
		assertEquals(5, countVertices());
		assertEquals(6, countEdges());
		assertEquals(2, universe1.getDemon().size());
	}
	
	@Test
	public void testSettingAll() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		Demon demon1 = new Demon(god);
		demon1.setName("demon1");
		Demon demon2 = new Demon(god);
		demon2.setName("demon2");
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		Set<Demon> demons = new HashSet<Demon>();
		demons.add(demon1);
		demons.add(demon2);
		universe1.addAllToDemon(demons);
		assertEquals(5, countVertices());
		assertEquals(6, countEdges());
		assertEquals(2, universe1.getDemon().size());
		universe2.addAllToDemon(demons);
		assertEquals(5, countVertices());
		assertEquals(6, countEdges());
		assertEquals(0, universe1.getDemon().size());
		assertEquals(2, universe2.getDemon().size());
		universe2.removeFromDemon(demon1);
		assertEquals(5, countVertices());
		assertEquals(5, countEdges());
		assertEquals(0, universe1.getDemon().size());
		assertEquals(1, universe2.getDemon().size());
		demon2.setUniverse(null);
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		assertEquals(0, universe1.getDemon().size());
		assertEquals(0, universe2.getDemon().size());
	}
	
	@Test
	public void testMarkDeleted() {
		God god = new God();
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		Demon demon1 = new Demon(god);
		demon1.setName("demon1");
		Demon demon2 = new Demon(god);
		demon2.setName("demon2");
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		Set<Demon> demons = new HashSet<Demon>();
		demons.add(demon1);
		demons.add(demon2);
		universe1.addAllToDemon(demons);
		assertEquals(5, countVertices());
		assertEquals(6, countEdges());
		assertEquals(2, universe1.getDemon().size());
		assertNotNull(demon1.getUniverse());
		assertNotNull(demon2.getUniverse());
		universe1.markDeleted();
		assertEquals(5, countVertices());
		assertEquals(6, countEdges());
		assertEquals(1, god.getUniverse().size());
		assertNull(demon1.getUniverse());
		assertNull(demon2.getUniverse());
	}		
}
