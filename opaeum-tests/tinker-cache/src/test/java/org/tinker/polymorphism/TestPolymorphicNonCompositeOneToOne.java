package org.tinker.polymorphism;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.opeum.test.tinker.BaseLocalDbTest;
import org.tinker.God;

public class TestPolymorphicNonCompositeOneToOne extends BaseLocalDbTest {

	@Test
	public void testSettingAndGetting() {
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
		assertEquals(5, countVertices());
		assertEquals(4, countEdges());
		
		concreteX1.setAbstractZ1(concreteZ1);
		assertEquals(5, countVertices());
		assertEquals(5, countEdges());
		assertEquals("concreteZ1", concreteX1.getAbstractZ1().getName());
		
		concreteZ2.setAbstractX1(concreteX2);
		assertEquals(5, countVertices());
		assertEquals(6, countEdges());
		assertEquals("concreteX2", concreteZ2.getAbstractX1().getName());
	}
}
