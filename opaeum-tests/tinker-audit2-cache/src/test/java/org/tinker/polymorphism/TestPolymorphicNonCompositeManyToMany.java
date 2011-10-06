package org.tinker.polymorphism;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.opeum.test.tinker.BaseLocalDbTest;
import org.tinker.God;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class TestPolymorphicNonCompositeManyToMany extends BaseLocalDbTest {

	@Test
	public void testSettingAndGetting() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		AbstractA1 abstractA1 = new ConcreteA1(god);
		AbstractA1 abstractA11 = new ConcreteA1(god);
		AbstractB abstractB1 = new ConcreteB1(god); 
		AbstractB abstractB11 = new ConcreteB1(god); 
		AbstractB abstractB2 = new ConcreteB2(god); 
		AbstractB abstractB21 = new ConcreteB2(god);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(14, countVertices());
		assertEquals(19, countEdges());
		
		db.startTransaction();
		abstractA1.addToAbstractB(abstractB1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(16, countVertices());
		assertEquals(23, countEdges());
		db.startTransaction();
		abstractA1.addToAbstractB(abstractB11);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(18, countVertices());
		assertEquals(27, countEdges());
		db.startTransaction();
		abstractA1.addToAbstractB(abstractB2);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(20, countVertices());
		assertEquals(31, countEdges());
		db.startTransaction();
		abstractA1.addToAbstractB(abstractB21);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(22, countVertices());
		assertEquals(35, countEdges());
		
		int concreteB1Count = 0;
		int concreteB2Count = 0;
		for(AbstractB abstractB : abstractA1.getAbstractB()) {
			if (abstractB instanceof ConcreteB1) {
				concreteB1Count++;	
			}
			if (abstractB instanceof ConcreteB2) {
				concreteB2Count++;	
			}
		}
		assertEquals(2, concreteB1Count);
		assertEquals(2, concreteB2Count);
		
		int concreteA1Count = 0;
		int concreteA2Count = 0;
		for(AbstractA1 abstractA : abstractB1.getAbstractA1()) {
			if (abstractA instanceof ConcreteA1) {
				concreteA1Count++;	
			}
			if (abstractA instanceof ConcreteA2) {
				concreteA2Count++;	
			}
		}
		assertEquals(1, concreteA1Count);
		assertEquals(0, concreteA2Count);	
		
		db.startTransaction();
		Set<AbstractB> abstractBs = new HashSet<AbstractB>();
		abstractBs.add(abstractB1);
		abstractBs.add(abstractB11);
		abstractBs.add(abstractB2);
		abstractBs.add(abstractB21);
		abstractA11.addAllToAbstractB(abstractBs);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(27, countVertices());
		assertEquals(48, countEdges());
		
	}
	
	@Test
	public void testAddingSameObjectTwice() {
		db.startTransaction();
		God god = new God();
		god.setName("THEGOD");
		AbstractA1 abstractA1 = new ConcreteA1(god);
		AbstractB abstractB1 = new ConcreteB1(god); 
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(6, countVertices());
		assertEquals(7, countEdges());
		db.startTransaction();
		abstractA1.addToAbstractB(abstractB1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(11, countEdges());
		db.startTransaction();
		abstractA1.addToAbstractB(abstractB1);
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(8, countVertices());
		assertEquals(11, countEdges());
	}
}
