package org.tinker.polymorphism;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;
import org.tinker.God;

public class TestPolymorphism extends BaseLocalDbTest {

	@Test
	public void testCreationAndNavigation() {
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
	
	@Test
	public void testCallingSet() {
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
		
		Set<AbstractY1> y = new HashSet<AbstractY1>();
		y.add(concreteY11);
		y.add(concreteY12);
		y.add(concreteY21);
		y.add(concreteY22);
		concreteX2.setAbstractY1(y);
		
		assertEquals(0, concreteX1.getAbstractY1().size());
		assertEquals(4, concreteX2.getAbstractY1().size());
		assertEquals(7, countVertices());
		assertEquals(6, countEdges());
		
		God otherGod = new God();
		otherGod.setName("OTHERGOD");
		concreteX2.setGod(otherGod);
		assertEquals("THEGOD", concreteX1.getGod().getName());
		assertEquals("OTHERGOD", concreteX2.getGod().getName());
		assertEquals("OTHERGOD", concreteY11.getAbstractX1().getGod().getName());
		assertEquals("OTHERGOD", concreteY12.getAbstractX1().getGod().getName());
		assertEquals("OTHERGOD", concreteY21.getAbstractX1().getGod().getName());
		assertEquals("OTHERGOD", concreteY22.getAbstractX1().getGod().getName());

		concreteX1.setAbstractY1(y);
		assertEquals(4, concreteX1.getAbstractY1().size());
		assertEquals(0, concreteX2.getAbstractY1().size());
		assertEquals(8, countVertices());
		assertEquals(6, countEdges());
		concreteX1.setGod(otherGod);
		assertEquals("OTHERGOD", concreteX1.getGod().getName());
		assertEquals("OTHERGOD", concreteX2.getGod().getName());
		assertEquals("OTHERGOD", concreteY11.getAbstractX1().getGod().getName());
		assertEquals("OTHERGOD", concreteY12.getAbstractX1().getGod().getName());
		assertEquals("OTHERGOD", concreteY21.getAbstractX1().getGod().getName());
		assertEquals("OTHERGOD", concreteY22.getAbstractX1().getGod().getName());
		
		ConcreteY1 concreteY111 = new ConcreteY1(concreteX2);
		concreteY11.setName("concreteY111");
		ConcreteY2 concreteY211 = new ConcreteY2(concreteX2);
		concreteY21.setName("concreteY211");
		ConcreteY1 concreteY121 = new ConcreteY1(concreteX2);
		concreteY12.setName("concreteY121");
		ConcreteY2 concreteY221 = new ConcreteY2(concreteX2);
		concreteY22.setName("concreteY221");
		Set<AbstractY1> y1 = new HashSet<AbstractY1>();
		y1.add(concreteY111);
		y1.add(concreteY121);
		y1.add(concreteY211);
		y1.add(concreteY221);
		concreteX2.setAbstractY1(y1);
		assertEquals(4, concreteX1.getAbstractY1().size());
		assertEquals(4, concreteX2.getAbstractY1().size());
		
		concreteX1.setAbstractY1(y1);
		assertEquals(4, concreteX1.getAbstractY1().size());
		assertEquals(0, concreteX2.getAbstractY1().size());
		assertEquals(12, countVertices());
		assertEquals(6, countEdges());
	}
	
	@Test
	public void testAddAll() {
		God god = new God();
		god.setName("THEGOD");
		ConcreteX1 concreteX1 = new ConcreteX1(god);
		concreteX1.setName("concreteX1");
		ConcreteX2 concreteX2 = new ConcreteX2(god);
		concreteX2.setName("concreteX2");
		assertEquals(3, countVertices());
		assertEquals(2, countEdges());
		ConcreteY1 concreteY11 = new ConcreteY1();
		concreteY11.setName("concreteY11");
		ConcreteY2 concreteY21 = new ConcreteY2();
		concreteY21.setName("concreteY21");
		ConcreteY1 concreteY12 = new ConcreteY1();
		concreteY12.setName("concreteY12");
		ConcreteY2 concreteY22 = new ConcreteY2();
		concreteY22.setName("concreteY22");
		assertEquals(7, countVertices());
		assertEquals(2, countEdges());
		
		Set<AbstractY1> y = new HashSet<AbstractY1>();
		y.add(concreteY11);
		y.add(concreteY12);
		y.add(concreteY21);
		y.add(concreteY22);
		concreteX2.addAllToAbstractY1(y);
		assertEquals(7, countVertices());
		assertEquals(6, countEdges());
	}
	
	@Test
	public void testMarkDeleted() {
		God god = new God();
		god.setName("THEGOD");
		ConcreteX1 concreteX11 = new ConcreteX1(god);
		concreteX11.setName("concrete11");
		ConcreteX1 concreteX12 = new ConcreteX1(god);
		concreteX12.setName("concrete12");
		ConcreteX2 concreteX21 = new ConcreteX2(god);
		concreteX21.setName("concrete21");
		ConcreteX2 concreteX22 = new ConcreteX2(god);
		concreteX22.setName("concrete22");
		
		ConcreteY1 concreteY11 = new ConcreteY1(concreteX11);
		concreteY11.setName("concreteY11");
		ConcreteY1 concreteY12 = new ConcreteY1(concreteX11);
		concreteY12.setName("concreteY12");
		ConcreteY2 concreteY21 = new ConcreteY2(concreteX21);
		concreteY21.setName("concreteY21");
		ConcreteY2 concreteY22 = new ConcreteY2(concreteX22);
		concreteY22.setName("concreteY22");
		
		assertEquals(9, countVertices());
		assertEquals(8, countEdges());
		concreteX11.markDeleted();
		assertEquals(9, countVertices());
		assertEquals(8, countEdges());
		assertEquals(3, god.getAbstractX1().size());
		concreteY21.markDeleted();
		assertEquals(9, countVertices());
		assertEquals(8, countEdges());
		assertEquals(3, god.getAbstractX1().size());
		assertEquals(0, concreteX21.getAbstractY1().size());
		assertEquals(1, concreteX22.getAbstractY1().size());
		
		concreteX22.markDeleted();
		assertEquals(9, countVertices());
		assertEquals(8, countEdges());
	}
	
}
