package org.nakeuml.tinker.concretetest;

import junit.framework.Assert;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.tinker.concretetest.Angel;
import org.tinker.concretetest.God;
import org.tinker.concretetest.Universe;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestNonCompositeOneToOne extends BaseLocalDbTest {

	@Test
	public void testNonCompositeOneToOneCreation() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Angel angel = new Angel(god);
		angel.setName("angel1");
		universe1.setAngel(angel);
		db.stopTransaction(Conclusion.SUCCESS);
		Universe universeTest = new Universe(universe1.getVertex());
		Assert.assertNotNull(universeTest.getAngel());
		Assert.assertEquals(7, countEdges());
	}

	@Test
	public void testNonCompositeOneToOneRemoval() {
		db.startTransaction();
		God god = new God(true);
		god.setName("THEGOD");
		Universe universe1 = new Universe(god);
		universe1.setName("universe1");
		Angel angel = new Angel(god);
		angel.setName("angel1");
		universe1.setAngel(angel);
		db.stopTransaction(Conclusion.SUCCESS);
		Universe universeTest = new Universe(universe1.getVertex());
		Assert.assertNotNull(universeTest.getAngel());
		Assert.assertEquals(7, countEdges());
		db.startTransaction();
		universeTest.setAngel(null);
		db.stopTransaction(Conclusion.SUCCESS);
		Universe universeTest2 = new Universe(universe1.getVertex());
		Assert.assertNull(universeTest2.getAngel());
		Assert.assertEquals(6, countEdges());
	}

}
