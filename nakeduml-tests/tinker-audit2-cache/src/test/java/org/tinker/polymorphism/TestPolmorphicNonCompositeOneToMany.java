package org.tinker.polymorphism;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.tinker.BaseLocalDbTest;
import org.tinker.God;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestPolmorphicNonCompositeOneToMany extends BaseLocalDbTest {

	@Test
	public void testSettingAndGetting() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		
		ConcreteX1 concreteX1 = new ConcreteX1(god);
		concreteX1.setName("concreteX1");
		ConcreteX2 concreteX2 = new ConcreteX2(god);
		concreteX2.setName("concreteX2");
		ConcreteA1 concreteA1 = new ConcreteA1(god);
		concreteA1.setName("concreteA1");
		ConcreteA2 concreteA2 = new ConcreteA2(god);
		concreteA2.setName("concreteA2");

		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());

		db.startTransaction();
		concreteX1.addToAbstractA1(concreteA1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(12, countVertices());
		assertEquals(17, countEdges());
		assertEquals(1, concreteX1.getAbstractA1().size());
		db.startTransaction();
		concreteX1.addToAbstractA1(concreteA2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(21, countEdges());
		assertEquals(2, concreteX1.getAbstractA1().size());

		db.startTransaction();
		Set<AbstractA1> concreteA1s = new HashSet<AbstractA1>();
		concreteA1s.add(concreteA1);
		concreteA1s.add(concreteA2);
		concreteX2.addAllToAbstractA1(concreteA1s);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(18, countVertices());
		assertEquals(29, countEdges());
		assertEquals(0, concreteX1.getAbstractA1().size());
		assertEquals(2, concreteX2.getAbstractA1().size());
		
		int concreteA1Count = 0;
		int concreteA2Count = 0;
		for(AbstractA1 abstractA1 : concreteX2.getAbstractA1()) {
			if (abstractA1 instanceof ConcreteA1) {
				concreteA1Count++;
			}
			if (abstractA1 instanceof ConcreteA2) {
				concreteA2Count++;
			}
		}
		assertTrue(concreteA1Count==1);
		assertTrue(concreteA2Count==1);
	}
	
	@Test
	public void testMarkDeleted() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		ConcreteX1 concreteX1 = new ConcreteX1(god);
		concreteX1.setName("concreteX1");
		ConcreteX2 concreteX2 = new ConcreteX2(god);
		concreteX2.setName("concreteX2");
		ConcreteA1 concreteA1 = new ConcreteA1(god);
		concreteA1.setName("concreteA1");
		ConcreteA2 concreteA2 = new ConcreteA2(god);
		concreteA2.setName("concreteA2");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(10, countVertices());
		assertEquals(13, countEdges());
		
		db.startTransaction();
		Set<AbstractA1> abstractA1s = new HashSet<AbstractA1>();
		abstractA1s.add(concreteA1);
		abstractA1s.add(concreteA2);
		concreteX1.addAllToAbstractA1(abstractA1s);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(13, countVertices());
		assertEquals(20, countEdges());
		assertEquals(2,god.getAbstractX1().size());
		
		db.startTransaction();
		concreteX1.markDeleted();
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(17, countVertices());
		assertEquals(27, countEdges());
		assertEquals(1,god.getAbstractX1().size());
	}
}
