package org.tinker.embedded;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.opeum.test.tinker.BaseLocalDbTest;
import org.tinker.Demon;
import org.tinker.God;

public class TestEmbeddedComplexTypes extends BaseLocalDbTest {

	@Test
	public void testEmbeddedSingleType() {
		God god = new God();
		god.setName("THEGOD");
		Demon demon1 = new Demon(god);
		demon1.setName("demon1");
		Demon demon2 = new Demon(god);
		demon2.setName("demon2");
		god.setDirectSingleDemon(demon1);
		assertNotNull(god.getDirectSingleDemon());
		assertEquals("demon1", god.getDirectSingleDemon().getName());
		god.setDirectSingleDemon(demon2);
		assertNotNull(god.getDirectSingleDemon());
		assertEquals("demon2", god.getDirectSingleDemon().getName());
	}
	
	@Test
	public void testEmbeddedMultiType() {
		God god = new God();
		god.setName("THEGOD");
		Demon demon1 = new Demon(god);
		demon1.setName("demon1");
		Demon demon2 = new Demon(god);
		demon2.setName("demon2");
		Demon demon3 = new Demon(god);
		demon3.setName("demon3");
		god.addToDirectMultiDemon(demon1);
		assertEquals(1,god.getDirectMultiDemon().size());
		god.addToDirectMultiDemon(demon2);
		assertEquals(2,god.getDirectMultiDemon().size());
		god.addToDirectMultiDemon(demon3);
		assertEquals(3,god.getDirectMultiDemon().size());
		god.removeFromDirectMultiDemon(demon1);
		assertEquals(2,god.getDirectMultiDemon().size());
	}	
}
