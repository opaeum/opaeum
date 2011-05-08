package org.tinker.polymorphism;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.tinker.BaseLocalDbTest;
import org.tinker.God;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestPolymorphism extends BaseLocalDbTest {

	@Test
	public void testCreationAndNavigation() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		ConcreteX1 concreteX1 = new ConcreteX1(god);
		concreteX1.setName("concreteX1");
		ConcreteX2 concreteX2 = new ConcreteX2(god);
		concreteX2.setName("concreteX2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(7, countEdges());
		db.startTransaction();
		ConcreteY1 concreteY11 = new ConcreteY1(concreteX1);
		concreteY11.setName("concreteY11");
		ConcreteY2 concreteY21 = new ConcreteY2(concreteX1);
		concreteY21.setName("concreteY21");
		ConcreteY1 concreteY12 = new ConcreteY1(concreteX1);
		concreteY12.setName("concreteY12");
		ConcreteY2 concreteY22 = new ConcreteY2(concreteX1);
		concreteY22.setName("concreteY22");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(15, countVertices());
		assertEquals(21, countEdges());
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
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		ConcreteX1 concreteX1 = new ConcreteX1(god);
		concreteX1.setName("concreteX1");
		ConcreteX2 concreteX2 = new ConcreteX2(god);
		concreteX2.setName("concreteX2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(7, countEdges());
		db.startTransaction();
		ConcreteY1 concreteY11 = new ConcreteY1(concreteX1);
		concreteY11.setName("concreteY11");
		ConcreteY2 concreteY21 = new ConcreteY2(concreteX1);
		concreteY21.setName("concreteY21");
		ConcreteY1 concreteY12 = new ConcreteY1(concreteX1);
		concreteY12.setName("concreteY12");
		ConcreteY2 concreteY22 = new ConcreteY2(concreteX1);
		concreteY22.setName("concreteY22");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(15, countVertices());
		assertEquals(21, countEdges());
		
		db.startTransaction();
		Set<AbstractY1> y = new HashSet<AbstractY1>();
		y.add(concreteY11);
		y.add(concreteY12);
		y.add(concreteY21);
		y.add(concreteY22);
		concreteX2.setAbstractY1(y);
		db.stopTransaction(Conclusion.SUCCESS);
		
		assertEquals(0, concreteX1.getAbstractY1().size());
		assertEquals(4, concreteX2.getAbstractY1().size());
		assertEquals(21, countVertices());
		assertEquals(41, countEdges());
		
		db.startTransaction();
		God otherGod = new God();
		otherGod.setName("OTHERGOD");
		concreteX2.setGod(otherGod);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(25, countVertices());
		assertEquals(48, countEdges());
		assertEquals("THEGOD", concreteX1.getGod().getName());
		assertEquals("OTHERGOD", concreteX2.getGod().getName());
		assertEquals("OTHERGOD", concreteY11.getAbstractX1().getGod().getName());
		assertEquals("OTHERGOD", concreteY12.getAbstractX1().getGod().getName());
		assertEquals("OTHERGOD", concreteY21.getAbstractX1().getGod().getName());
		assertEquals("OTHERGOD", concreteY22.getAbstractX1().getGod().getName());

		db.startTransaction();
		concreteX1.setAbstractY1(y);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(4, concreteX1.getAbstractY1().size());
		assertEquals(0, concreteX2.getAbstractY1().size());
		assertEquals(31, countVertices());
		assertEquals(68, countEdges());
		db.startTransaction();
		concreteX1.setGod(otherGod);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(34, countVertices());
		assertEquals(76, countEdges());
		assertEquals("OTHERGOD", concreteX1.getGod().getName());
		assertEquals("OTHERGOD", concreteX2.getGod().getName());
		assertEquals("OTHERGOD", concreteY11.getAbstractX1().getGod().getName());
		assertEquals("OTHERGOD", concreteY12.getAbstractX1().getGod().getName());
		assertEquals("OTHERGOD", concreteY21.getAbstractX1().getGod().getName());
		assertEquals("OTHERGOD", concreteY22.getAbstractX1().getGod().getName());
		
		db.startTransaction();
		ConcreteY1 concreteY111 = new ConcreteY1(concreteX2);
		concreteY111.setName("concreteY111");
		ConcreteY2 concreteY211 = new ConcreteY2(concreteX2);
		concreteY211.setName("concreteY211");
		ConcreteY1 concreteY121 = new ConcreteY1(concreteX2);
		concreteY121.setName("concreteY121");
		ConcreteY2 concreteY221 = new ConcreteY2(concreteX2);
		concreteY221.setName("concreteY221");
		Set<AbstractY1> y1 = new HashSet<AbstractY1>();
		y1.add(concreteY111);
		y1.add(concreteY121);
		y1.add(concreteY211);
		y1.add(concreteY221);
		concreteX2.setAbstractY1(y1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(43, countVertices());
		assertEquals(90, countEdges());
		assertEquals(4, concreteX1.getAbstractY1().size());
		assertEquals(4, concreteX2.getAbstractY1().size());
		
		db.startTransaction();
		concreteX1.setAbstractY1(y1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(4, concreteX1.getAbstractY1().size());
		assertEquals(0, concreteX2.getAbstractY1().size());
		assertEquals(53, countVertices());
		assertEquals(118, countEdges());
	}
	
	@Test
	public void testAddAll() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		ConcreteX1 concreteX1 = new ConcreteX1(god);
		concreteX1.setName("concreteX1");
		ConcreteX2 concreteX2 = new ConcreteX2(god);
		concreteX2.setName("concreteX2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(7, countEdges());
		db.startTransaction();
		ConcreteY1 concreteY11 = new ConcreteY1();
		concreteY11.setName("concreteY11");
		ConcreteY2 concreteY21 = new ConcreteY2();
		concreteY21.setName("concreteY21");
		ConcreteY1 concreteY12 = new ConcreteY1();
		concreteY12.setName("concreteY12");
		ConcreteY2 concreteY22 = new ConcreteY2();
		concreteY22.setName("concreteY22");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(11, countEdges());
		db.startTransaction();
		Set<AbstractY1> y = new HashSet<AbstractY1>();
		y.add(concreteY11);
		y.add(concreteY12);
		y.add(concreteY21);
		y.add(concreteY22);
		concreteX2.addAllToAbstractY1(y);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(19, countVertices());
		assertEquals(29, countEdges());
	}
	
	@Test
	public void testMarkDeleted() {
		db.startTransaction();
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
		db.stopTransaction(Conclusion.SUCCESS);
		
		assertEquals(18, countVertices());
		assertEquals(25, countEdges());
		db.startTransaction();
		concreteX11.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(21, countVertices());
		assertEquals(31, countEdges());
		assertEquals(3, god.getAbstractX1().size());
		db.startTransaction();
		concreteY21.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(22, countVertices());
		assertEquals(33, countEdges());
		assertEquals(3, god.getAbstractX1().size());
		assertEquals(0, concreteX21.getAbstractY1().size());
		assertEquals(1, concreteX22.getAbstractY1().size());
		
		db.startTransaction();
		concreteX22.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(24, countVertices());
		assertEquals(37, countEdges());
	}
	
}
