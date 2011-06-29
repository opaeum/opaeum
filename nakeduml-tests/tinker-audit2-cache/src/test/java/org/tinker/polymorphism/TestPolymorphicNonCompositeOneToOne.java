package org.tinker.polymorphism;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;
import org.tinker.God;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestPolymorphicNonCompositeOneToOne extends BaseLocalDbTest {

	@Test
	public void testSettingAndGetting() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		ConcreteX1 concreteX1 = new ConcreteX1(god);
		concreteX1.setName("concreteX1");
		ConcreteX2 concreteX2 = new ConcreteX2(god);
		concreteX2.setName("concreteX2");
		ConcreteZ1 concreteZ1 = new ConcreteZ1(god);
		concreteZ1.setName("concreteZ1");
		ConcreteZ2 concreteZ2 = new ConcreteZ2(god);
		concreteZ2.setName("concreteZ2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());

		db.startTransaction();
		concreteX1.setAbstractZ1(concreteZ1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(17, countEdges());
		assertEquals("concreteZ1", concreteX1.getAbstractZ1().getName());

		db.startTransaction();
		concreteZ2.setAbstractX1(concreteX2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(21, countEdges());
		assertEquals("concreteX2", concreteZ2.getAbstractX1().getName());
	}
}
