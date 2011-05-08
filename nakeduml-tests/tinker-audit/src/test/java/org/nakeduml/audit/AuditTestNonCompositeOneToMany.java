package org.nakeduml.audit;

import static org.junit.Assert.assertEquals;

import org.audittest.Finger;
import org.audittest.Hand;
import org.audittest.Ring;
import org.junit.Test;
import org.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class AuditTestNonCompositeOneToMany extends BaseLocalDbTest {

		@Test
		public void testFingerAndRings() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand1");
			Finger finger1 = new Finger(hand);
			finger1.setName("finger1");
			Finger finger2 = new Finger(hand);
			finger2.setName("finger2");
			Ring ring1 = new Ring(hand);
			ring1.setName("ring1");
			Ring ring2 = new Ring(hand);
			ring2.setName("ring2");
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(10, countVertices());
			assertEquals(13, countEdges());
		}
		
		@Test
		public void testFingerAndRingsAgain() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand1");
			Finger finger1 = new Finger(hand);
			finger1.setName("finger1");
			Finger finger2 = new Finger(hand);
			finger2.setName("finger2");
			Ring ring1 = new Ring(hand);
			ring1.setName("ring1");
			Ring ring2 = new Ring(hand);
			ring2.setName("ring2");
			finger1.addToRing(ring1);
			finger2.addToRing(ring2);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(10, countVertices());
			assertEquals(17, countEdges());
		}
		
		@Test
		public void testFingerAndRingTwoTransactions() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand1");
			Finger finger1 = new Finger(hand);
			finger1.setName("finger1");
			Finger finger2 = new Finger(hand);
			finger2.setName("finger2");
			Ring ring1 = new Ring(hand);
			ring1.setName("ring1");
			finger1.addToRing(ring1);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(8, countVertices());
			assertEquals(12, countEdges());
			db.startTransaction();
			finger2.addToRing(ring1);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(11, countVertices());
			assertEquals(20, countEdges());
		}
		
		@Test
		public void testFingerAndRingsTwoTransactions() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand1");
			Finger finger1 = new Finger(hand);
			finger1.setName("finger1");
			Finger finger2 = new Finger(hand);
			finger2.setName("finger2");
			Ring ring1 = new Ring(hand);
			ring1.setName("ring1");
			Ring ring2 = new Ring(hand);
			ring2.setName("ring2");
			finger1.addToRing(ring1);
			finger2.addToRing(ring2);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(1,finger1.getRing().size());
			assertEquals(10, countVertices());
			assertEquals(17, countEdges());
			db.startTransaction();
			finger2.addToRing(ring1);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(0,finger1.getRing().size());
			assertEquals(13, countVertices());
			assertEquals(25, countEdges());
		}
		
		@Test
		public void testFingersAndRingsTwoTransactions() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand1");
			Finger finger1 = new Finger(hand);
			finger1.setName("finger1");
			Finger finger2 = new Finger(hand);
			finger2.setName("finger2");
			Ring ring1 = new Ring(hand);
			ring1.setName("ring1");
			Ring ring2 = new Ring(hand);
			ring2.setName("ring2");
			finger1.addToRing(ring1);
			finger2.addToRing(ring2);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(1,finger1.getRing().size());
			assertEquals(10, countVertices());
			assertEquals(17, countEdges());
			db.startTransaction();
			finger2.addToRing(ring1);
			finger1.addToRing(ring2);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(1,finger1.getRing().size());
			assertEquals(1,finger2.getRing().size());
			assertEquals(14, countVertices());
			assertEquals(29, countEdges());
		}
		
		@Test
		public void testSetFingerToNullOnRing() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand1");
			Finger finger1 = new Finger(hand);
			finger1.setName("finger1");
			Finger finger2 = new Finger(hand);
			finger2.setName("finger2");
			Ring ring1 = new Ring(hand);
			ring1.setName("ring1");
			Ring ring2 = new Ring(hand);
			ring2.setName("ring2");
			finger1.addToRing(ring1);
			finger2.addToRing(ring2);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(1,finger1.getRing().size());
			assertEquals(10, countVertices());
			assertEquals(17, countEdges());
			db.startTransaction();
			ring1.setFinger(null);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(0,finger1.getRing().size());
			assertEquals(1,finger2.getRing().size());
			assertEquals(12, countVertices());
			assertEquals(21, countEdges());
		}			
		
}
