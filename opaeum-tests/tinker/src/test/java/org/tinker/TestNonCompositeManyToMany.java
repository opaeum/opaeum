package org.tinker;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

public class TestNonCompositeManyToMany extends BaseLocalDbTest {

	@Test
	public void testSettingAndGetting() {
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
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		demon1.addToDreamWorld(dreamWorld1);
		assertEquals(5, countVertices());
		assertEquals(5, countEdges());
		demon1.addToDreamWorld(dreamWorld2);
		assertEquals(5, countVertices());
		assertEquals(6, countEdges());
		Set<DreamWorld> dreamWorlds = new HashSet<DreamWorld>();
		dreamWorlds.add(dreamWorld1);
		dreamWorlds.add(dreamWorld2);
		demon2.setDreamWorld(dreamWorlds);
		assertEquals(5, countVertices());
		assertEquals(8, countEdges());
		assertEquals("THEGOD", demon1.getDreamWorld().iterator().next().getGod().getName());
		assertEquals("THEGOD", dreamWorld1.getDemon().iterator().next().getGod().getName());
		assertEquals(2, dreamWorld1.getDemon().size());
		assertEquals(2, dreamWorld2.getDemon().size());
		assertEquals(2, demon1.getDreamWorld().size());
		assertEquals(2, demon2.getDreamWorld().size());
	}
	
	@Test
	public void testRemoving() {
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
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		Set<DreamWorld> dreamWorlds = new HashSet<DreamWorld>();
		dreamWorlds.add(dreamWorld1);
		dreamWorlds.add(dreamWorld2);
		demon2.setDreamWorld(dreamWorlds);
		assertEquals(5, countVertices());
		assertEquals(6, countEdges());
		assertEquals("THEGOD", demon2.getDreamWorld().iterator().next().getGod().getName());
		assertEquals("THEGOD", dreamWorld1.getDemon().iterator().next().getGod().getName());
		assertEquals(1, dreamWorld1.getDemon().size());
		assertEquals(1, dreamWorld2.getDemon().size());
		assertEquals(2, demon2.getDreamWorld().size());
		demon2.removeFromDreamWorld(dreamWorld1);
		assertEquals(5, countEdges());
		demon2.removeFromDreamWorld(dreamWorld2);
		assertEquals(4, countEdges());
		demon1.addAllToDreamWorld(dreamWorlds);
		assertEquals(6, countEdges());
		demon1.removeAllFromDreamWorld(dreamWorlds);
		assertEquals(4, countEdges());
		
		demon1.setDreamWorld(dreamWorlds);
		demon2.setDreamWorld(dreamWorlds);
		assertEquals(5, countVertices());
		assertEquals(8, countEdges());
		DreamWorld dreamWorld3 = new DreamWorld(god);
		DreamWorld dreamWorld4 = new DreamWorld(god);
		assertEquals(7, countVertices());
		assertEquals(10, countEdges());
		Set<DreamWorld> dreamWorlds2 = new HashSet<DreamWorld>();
		dreamWorlds2.add(dreamWorld3);
		dreamWorlds2.add(dreamWorld4);
		demon1.setDreamWorld(dreamWorlds2);
		assertEquals(7, countVertices());
		assertEquals(10, countEdges());
	}	
}
