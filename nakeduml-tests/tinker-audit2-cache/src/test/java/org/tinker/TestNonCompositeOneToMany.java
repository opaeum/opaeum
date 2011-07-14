package org.tinker;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestNonCompositeOneToMany extends BaseLocalDbTest {

	@Test
	public void testSettingAndGetting() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		Demon demon1 = new Demon(god);
		demon1.setName("demon1");
		Demon demon2 = new Demon(god);
		demon2.setName("demon2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(14, countEdges());
		db.startTransaction();
		universe1.addToDemon(demon1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(18, countEdges());
		assertEquals(1, universe1.getDemon().size());
		db.startTransaction();
		universe1.addToDemon(demon2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(22, countEdges());
		assertEquals(2, universe1.getDemon().size());
	}
	
	@Test
	public void testSettingAll() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		Demon demon1 = new Demon(god);
		demon1.setName("demon1");
		Demon demon2 = new Demon(god);
		demon2.setName("demon2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(14, countEdges());
		db.startTransaction();
		Set<Demon> demons = new HashSet<Demon>();
		demons.add(demon1);
		demons.add(demon2);
		universe1.addAllToDemon(demons);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(13, countVertices());
		assertEquals(21, countEdges());
		assertEquals(2, universe1.getDemon().size());
		db.startTransaction();
		universe2.addAllToDemon(demons);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(17, countVertices());
		assertEquals(29, countEdges());
		assertEquals(0, universe1.getDemon().size());
		assertEquals(2, universe2.getDemon().size());
		db.startTransaction();
		universe2.removeFromDemon(demon1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(19, countVertices());
		assertEquals(31, countEdges());
		assertEquals(0, universe1.getDemon().size());
		assertEquals(1, universe2.getDemon().size());
		db.startTransaction();
		demon2.setUniverse(null);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(21, countVertices());
		assertEquals(33, countEdges());
		assertEquals(0, universe1.getDemon().size());
		assertEquals(0, universe2.getDemon().size());
	}
	
	@Test
	public void testMarkDeleted() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Universe universe2 = new Universe(god);
		universe2.setName("universe2");
		Demon demon1 = new Demon(god);
		demon1.setName("demon1");
		Demon demon2 = new Demon(god);
		demon2.setName("demon2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(14, countEdges());
		db.startTransaction();
		Set<Demon> demons = new HashSet<Demon>();
		demons.add(demon1);
		demons.add(demon2);
		universe1.addAllToDemon(demons);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(13, countVertices());
		assertEquals(21, countEdges());
		assertEquals(2, universe1.getDemon().size());
		db.startTransaction();
		universe1.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(17, countVertices());
		assertEquals(28, countEdges());
	}		
}
