package org.nakeduml.audit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.Vertex;

public class TestOrientRemoveEdgeBug extends BaseLocalDbTest {

	protected long countVertices() {
		return db.countVertices();
	}

	protected long countEdges() {
		 return db.countEdges();
	}	
	
	@Test
	public void test() {
		db.startTransaction();
		Vertex vertexHand = db.addVertex(null);
		Vertex vertexAuditHand = db.addVertex(null);
		Edge handOriginalEdge  = db.addEdge(null, vertexHand, vertexAuditHand, "handOriginalEdge");
		Vertex vertexGlove = db.addVertex(null);
		Vertex vertexAuditGlove = db.addVertex(null);
		Edge handGlove  = db.addEdge(null, vertexHand, vertexGlove, "handGlove");
		Edge gloveOriginalEdge  = db.addEdge(null, vertexHand, vertexAuditGlove, "gloveOriginalEdge");
		Edge handGloveAuditEdge  = db.addEdge(null, vertexAuditHand, vertexAuditGlove, "handGloveAuditEdge");
		db.stopTransaction(Conclusion.SUCCESS);
		assertEquals(4, countVertices());
		assertEquals(4, countEdges());
		db.startTransaction();
		
		Vertex vertexAuditGlove2 = db.addVertex(null);
		Edge gloveOriginalEdge2  = db.addEdge(null, vertexGlove, vertexAuditGlove2, "gloveOriginalEdge2");
		Edge glovePreviousEdge  = db.addEdge(null, vertexAuditGlove2, vertexAuditGlove, "glovePreviousEdge");
		Edge handGloveAuditEdge1  = db.addEdge(null, vertexAuditHand, vertexAuditGlove2, "handGloveAuditEdge1");
		
		Vertex vertexAuditHand2 = db.addVertex(null);
		Edge handOriginalEdge2  = db.addEdge(null, vertexHand, vertexAuditHand2, "handOriginalEdge2");
		Edge handPreviousEdge  = db.addEdge(null, vertexAuditHand2, vertexAuditHand, "handPreviousEdge");
		Edge handGloveAuditEdge2  = db.addEdge(null, vertexAuditHand2, vertexAuditGlove2, "handGloveAuditEdge2");

		assertTrue( vertexAuditGlove2.getInEdges("handGloveAuditEdge1").iterator().hasNext() );
		assertTrue( vertexAuditHand.getOutEdges("handGloveAuditEdge1").iterator().hasNext() );
		
		db.removeEdge(handGloveAuditEdge1);
		db.stopTransaction(Conclusion.SUCCESS);

		assertFalse( vertexAuditGlove2.getInEdges("handGloveAuditEdge1").iterator().hasNext() );
		assertFalse( vertexAuditHand.getOutEdges("handGloveAuditEdge1").iterator().hasNext() );
		assertEquals(6, countVertices());
		assertEquals(9, countEdges());		
	}
}
