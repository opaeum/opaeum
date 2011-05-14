package org.tinker;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestNonCompositeManyToMany extends BaseLocalDbTest {

//	@Test
	public void testSettingAndGetting() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Demon demon1 = new Demon(god);
		demon1.setName("demon1");
		Demon demon2 = new Demon(god);
		demon2.setName("demon2");
		DreamWorld dreamWorld1 = new DreamWorld(god);
		dreamWorld1.setName("dreamWorld1");
		DreamWorld dreamWorld2 = new DreamWorld(god);
		dreamWorld2.setName("dreamWorld2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());
		db.startTransaction();
		demon1.addToDreamWorld(dreamWorld1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(21, countEdges());
		db.startTransaction();
		demon1.addToDreamWorld(dreamWorld2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(29, countEdges());
		db.startTransaction();
		Set<DreamWorld> dreamWorlds = new HashSet<DreamWorld>();
		dreamWorlds.add(dreamWorld1);
		dreamWorlds.add(dreamWorld2);
		demon2.setDreamWorld(dreamWorlds);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(17, countVertices());
		assertEquals(42, countEdges());
		assertEquals("THEGOD", demon1.getDreamWorld().iterator().next().getGod().getName());
		assertEquals("THEGOD", dreamWorld1.getDemon().iterator().next().getGod().getName());
		assertEquals(2, dreamWorld1.getDemon().size());
		assertEquals(2, dreamWorld2.getDemon().size());
		assertEquals(2, demon1.getDreamWorld().size());
		assertEquals(2, demon2.getDreamWorld().size());
	}
	
	@Test
	public void testRemoving() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		Demon demon1 = new Demon(god);
		demon1.setName("demon1");
		Demon demon2 = new Demon(god);
		demon2.setName("demon2");
		DreamWorld dreamWorld1 = new DreamWorld(god);
		dreamWorld1.setName("dreamWorld1");
		DreamWorld dreamWorld2 = new DreamWorld(god);
		dreamWorld2.setName("dreamWorld2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());
		db.startTransaction();
		Set<DreamWorld> dreamWorlds = new HashSet<DreamWorld>();
		dreamWorlds.add(dreamWorld1);
		dreamWorlds.add(dreamWorld2);
		demon2.setDreamWorld(dreamWorlds);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(13, countVertices());
		assertEquals(20, countEdges());
		assertEquals("THEGOD", demon2.getDreamWorld().iterator().next().getGod().getName());
		assertEquals("THEGOD", dreamWorld1.getDemon().iterator().next().getGod().getName());
		assertEquals(1, dreamWorld1.getDemon().size());
		assertEquals(1, dreamWorld2.getDemon().size());
		assertEquals(2, demon2.getDreamWorld().size());
		db.startTransaction();
		demon2.removeFromDreamWorld(dreamWorld1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(15, countVertices());
		assertEquals(22, countEdges());
		db.startTransaction();
		demon2.removeFromDreamWorld(dreamWorld2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(17, countVertices());
		assertEquals(24, countEdges());
		db.startTransaction();
		demon1.addAllToDreamWorld(dreamWorlds);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(20, countVertices());
		assertEquals(31, countEdges());
		db.startTransaction();
		demon1.removeAllFromDreamWorld(dreamWorlds);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(23, countVertices());
		assertEquals(34, countEdges());
		db.startTransaction();
		demon1.setDreamWorld(dreamWorlds);
		demon2.setDreamWorld(dreamWorlds);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(27, countVertices());
		assertEquals(46, countEdges());
		db.startTransaction();
		DreamWorld dreamWorld3 = new DreamWorld(god);
		DreamWorld dreamWorld4 = new DreamWorld(god);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(32, countVertices());
		assertEquals(53, countEdges());
		db.startTransaction();
		Set<DreamWorld> dreamWorlds2 = new HashSet<DreamWorld>();
		dreamWorlds2.add(dreamWorld3);
		dreamWorlds2.add(dreamWorld4);
		demon1.setDreamWorld(dreamWorlds2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(37, countVertices());
		assertEquals(62, countEdges());
	}	
}
