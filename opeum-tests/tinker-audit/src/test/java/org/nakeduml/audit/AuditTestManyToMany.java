package org.opeum.audit;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.audittest.Hand;
import org.audittest.ManyA;
import org.audittest.ManyB;
import org.junit.Test;
import org.opeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;

public class AuditTestManyToMany extends BaseLocalDbTest {

		@Test
		public void testCaptureManyAandB() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand1");
			ManyA manyA1 = new ManyA(hand);
			manyA1.setName("manyA1");
			ManyA manyA2 = new ManyA(hand);
			manyA2.setName("manyA2");
			ManyB manyB1 = new ManyB(hand);
			manyB1.setName("manyB1");
			ManyB manyB2 = new ManyB(hand);
			manyB2.setName("manyB2");
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(10, countVertices());
			assertEquals(13, countEdges());
		}

		@Test
		public void testCaptureManyAandBs() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand1");
			ManyA manyA1 = new ManyA(hand);
			manyA1.setName("manyA1");
			ManyA manyA2 = new ManyA(hand);
			manyA2.setName("manyA2");
			ManyB manyB1 = new ManyB(hand);
			manyB1.setName("manyB1");
			ManyB manyB2 = new ManyB(hand);
			manyB2.setName("manyB2");
			manyA1.addToManyB(manyB1);
			manyA1.addToManyB(manyB2);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(10, countVertices());
			assertEquals(17, countEdges());
		}
		
		@Test
		public void testCaptureManyAandBss() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand1");
			ManyA manyA1 = new ManyA(hand);
			manyA1.setName("manyA1");
			ManyA manyA2 = new ManyA(hand);
			manyA2.setName("manyA2");
			ManyB manyB1 = new ManyB(hand);
			manyB1.setName("manyB1");
			ManyB manyB2 = new ManyB(hand);
			manyB2.setName("manyB2");
			manyA1.addToManyB(manyB1);
			manyA1.addToManyB(manyB2);
			manyA2.addToManyB(manyB1);
			manyA2.addToManyB(manyB2);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(10, countVertices());
			assertEquals(21, countEdges());
		}
		
		@Test
		public void testCaptureManyAandBsTwoTransactions() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand1");
			ManyA manyA1 = new ManyA(hand);
			manyA1.setName("manyA1");
			ManyB manyB1 = new ManyB(hand);
			manyB1.setName("manyB1");
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(6, countVertices());
			assertEquals(7, countEdges());
			db.startTransaction();
			manyA1.addToManyB(manyB1);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(8, countVertices());
			assertEquals(11, countEdges());
		}
		
		@Test
		public void testCaptureRemovingFromManyToMany() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand1");
			ManyA manyA1 = new ManyA(hand);
			manyA1.setName("manyA1");
			ManyA manyA2 = new ManyA(hand);
			manyA2.setName("manyA2");
			ManyB manyB1 = new ManyB(hand);
			manyB1.setName("manyB1");
			ManyB manyB2 = new ManyB(hand);
			manyB2.setName("manyB2");
			manyA1.addToManyB(manyB1);
			manyA1.addToManyB(manyB2);
			manyA2.addToManyB(manyB1);
			manyA2.addToManyB(manyB2);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(10, countVertices());
			assertEquals(21, countEdges());
			db.startTransaction();
			manyA1.removeFromManyB(manyB1);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(12, countVertices());
			assertEquals(23, countEdges());
		}
		
		@Test
		public void testCallingSetOnManyToMany() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand1");
			ManyA manyA1 = new ManyA(hand);
			manyA1.setName("manyA1");
			ManyA manyA2 = new ManyA(hand);
			manyA2.setName("manyA2");
			ManyB manyB1 = new ManyB(hand);
			manyB1.setName("manyB1");
			ManyB manyB2 = new ManyB(hand);
			manyB2.setName("manyB2");
			manyA2.addToManyB(manyB1);
			manyA2.addToManyB(manyB2);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(10, countVertices());
			assertEquals(17, countEdges());
			db.startTransaction();
			Set<ManyA> manyAs = new HashSet<ManyA>();
			manyAs.add(manyA1);
			manyB1.setManyA(manyAs);
			manyB2.setManyA(manyAs);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(14, countVertices());
			assertEquals(25, countEdges());
		}
		
		@Test
		public void testAddingSameObjectTwice() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand1");
			ManyA manyA1 = new ManyA(hand);
			manyA1.setName("manyA1");
			ManyB manyB1 = new ManyB(hand);
			manyB1.setName("manyB1");
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(6, countVertices());
			assertEquals(7, countEdges());
			db.startTransaction();
			manyA1.addToManyB(manyB1);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(8, countVertices());
			assertEquals(11, countEdges());
			db.startTransaction();
			manyA1.addToManyB(manyB1);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(8, countVertices());
			assertEquals(11, countEdges());
		}
		
		@Test
		public void testSimpleManyToManyRemoval() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand");
			ManyA manyA = new ManyA(hand);
			manyA.setName("manyA");
			ManyB manyB = new ManyB(hand);
			manyB.setName("manyB");
			manyA.addToManyB(manyB);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(6, countVertices());
			assertEquals(9, countEdges());
			db.startTransaction();
			manyA.removeFromManyB(manyB);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(8, countVertices());
			assertEquals(11, countEdges());
		}
		
		@Test
		public void testSimpleManyToManyRemovalViaSet() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand");
			ManyA manyA = new ManyA(hand);
			manyA.setName("manyA");
			ManyB manyB = new ManyB(hand);
			manyB.setName("manyB");
			manyA.addToManyB(manyB);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(6, countVertices());
			assertEquals(9, countEdges());
			db.startTransaction();
			ManyB manyB2 = new ManyB(hand);
			manyB2.setName("manyB2");
			Set<ManyB> manyBs = new  HashSet<ManyB>();
			manyBs.add(manyB2);
			manyA.setManyB(manyBs);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(11, countVertices());
			assertEquals(17, countEdges());
		}	
		
		@Test
		public void testSimpleManyToManySetNull() {
			db.startTransaction();
			Hand hand = new Hand();
			hand.setName("hand");
			ManyA manyA = new ManyA(hand);
			manyA.setName("manyA");
			ManyB manyB = new ManyB(hand);
			manyB.setName("manyB");
			manyA.addToManyB(manyB);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(6, countVertices());
			assertEquals(9, countEdges());
			db.startTransaction();
			manyA.setManyB(null);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(8, countVertices());
			assertEquals(11, countEdges());
		}		
		
		
}
