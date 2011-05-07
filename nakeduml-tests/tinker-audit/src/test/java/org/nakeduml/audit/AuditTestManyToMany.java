package org.nakeduml.audit;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.audittest.Hand;
import org.audittest.ManyA;
import org.audittest.ManyB;
import org.junit.Test;
import org.tinker.BaseLocalDbTest;

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
			assertEquals(13, countEdges());
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
			assertEquals(24, countEdges());
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
			Set<ManyA> manyAs = new HashSet<ManyA>();
			manyAs.add(manyA1);
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
			manyB1.setManyA(manyAs);
			manyB2.setManyA(manyAs);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(14, countVertices());
			assertEquals(27, countEdges());
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
			assertEquals(13, countEdges());
			db.startTransaction();
			manyA1.addToManyB(manyB1);
			db.stopTransaction(Conclusion.SUCCESS);
			assertEquals(8, countVertices());
			assertEquals(13, countEdges());
		}
		
}
