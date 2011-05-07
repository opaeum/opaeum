package org.tinker.polymorphism;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.tinker.BaseTest;
import org.tinker.God;

public class TestPolymorphicNonCompositeManyToMany extends BaseTest {

//	@Test
	public void testSettingAndGetting() {
		God god = new God();
		god.setName("THEGOD");
		AbstractA1 abstractA1 = new ConcreteA1(god);
		AbstractA1 abstractA11 = new ConcreteA1(god);
		AbstractB abstractB1 = new ConcreteB1(god); 
		AbstractB abstractB11 = new ConcreteB1(god); 
		AbstractB abstractB2 = new ConcreteB2(god); 
		AbstractB abstractB21 = new ConcreteB2(god);
		assertEquals(7, countVertices());
		assertEquals(6, countEdges());
		
		abstractA1.addToAbstractB(abstractB1);
		assertEquals(7, countVertices());
		assertEquals(7, countEdges());
		abstractA1.addToAbstractB(abstractB11);
		assertEquals(8, countEdges());
		abstractA1.addToAbstractB(abstractB2);
		assertEquals(9, countEdges());
		abstractA1.addToAbstractB(abstractB21);
		assertEquals(10, countEdges());
		
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
		
		Set<AbstractB> abstractBs = new HashSet<AbstractB>();
		abstractBs.add(abstractB1);
		abstractBs.add(abstractB11);
		abstractBs.add(abstractB2);
		abstractBs.add(abstractB21);
		abstractA11.addAllToAbstractB(abstractBs);
		assertEquals(14, countEdges());
		
	}
	
	@Test
	public void testAddingSameObjectTwice() {
		God god = new God();
		god.setName("THEGOD");
		AbstractA1 abstractA1 = new ConcreteA1(god);
		AbstractB abstractB1 = new ConcreteB1(god); 
		assertEquals(3, countVertices());
		assertEquals(2, countEdges());
		abstractA1.addToAbstractB(abstractB1);
		assertEquals(3, countVertices());
		assertEquals(3, countEdges());
		abstractA1.addToAbstractB(abstractB1);
		assertEquals(3, countVertices());
		assertEquals(3, countEdges());
	}
}
