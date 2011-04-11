package org.tinker.polymorphism;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tinker.BaseTest;
import org.tinker.God;

public class TestPolymorphism extends BaseTest {

	@Test
	public void test() {
		God god = new God();
		god.setName("THEGOD");
		ConcreteX1 concreteX1 = new ConcreteX1(god);
		concreteX1.setName("concreteX1");
		ConcreteX2 concreteX2 = new ConcreteX2(god);
		concreteX2.setName("concreteX2");
		assertEquals(3, countVertices());
		assertEquals(2, countEdges());
		ConcreteY1 concreteY11 = new ConcreteY1(concreteX1);
		concreteY11.setName("concreteY11");
		ConcreteY2 concreteY21 = new ConcreteY2(concreteX1);
		concreteY21.setName("concreteY21");
		ConcreteY1 concreteY12 = new ConcreteY1(concreteX1);
		concreteY12.setName("concreteY12");
		ConcreteY2 concreteY22 = new ConcreteY2(concreteX1);
		concreteY22.setName("concreteY22");
		assertEquals(7, countVertices());
		assertEquals(6, countEdges());
		assertEquals(2, god.getAbstractX1().size());
		assertEquals("THEGOD", concreteX1.getGod().getName());
		assertEquals("THEGOD", concreteX2.getGod().getName());
		assertEquals("THEGOD", concreteY11.getAbstractX1().getGod().getName());
		assertEquals("THEGOD", concreteY12.getAbstractX1().getGod().getName());
		assertEquals("THEGOD", concreteY21.getAbstractX1().getGod().getName());
		assertEquals("THEGOD", concreteY22.getAbstractX1().getGod().getName());
		for (AbstractX1 abstractX1 : god.getAbstractX1()) {
			assertTrue((abstractX1 instanceof ConcreteX1) || (abstractX1 instanceof ConcreteX2));
		}
		for (AbstractY1 abstractY1 : concreteX1.getAbstractY1()) {
			assertTrue((abstractY1 instanceof ConcreteY1) || (abstractY1 instanceof ConcreteY2));
		}
		for (AbstractY1 abstractY1 : concreteX2.getAbstractY1()) {
			assertTrue((abstractY1 instanceof ConcreteY1) || (abstractY1 instanceof ConcreteY2));
		}
		assertTrue((concreteY11.getAbstractX1() instanceof ConcreteX1) || (concreteY11.getAbstractX1() instanceof ConcreteX2));
		assertTrue((concreteY12.getAbstractX1() instanceof ConcreteX1) || (concreteY12.getAbstractX1() instanceof ConcreteX2));
		assertTrue((concreteY21.getAbstractX1() instanceof ConcreteX1) || (concreteY21.getAbstractX1() instanceof ConcreteX2));
		assertTrue((concreteY22.getAbstractX1() instanceof ConcreteX1) || (concreteY22.getAbstractX1() instanceof ConcreteX2));
	}
}
