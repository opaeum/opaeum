package org.tinker.tinkerbug;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;
import org.opaeum.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public class TestTinkerEdgeDeletion extends BaseLocalDbTest {
	
	@After
	public void after() {
		db.shutdown();
	}
	
	@Test
	public void testTinkerBug() {
		Vertex one1 = db.addVertex(null);
		Vertex one2 = db.addVertex(null);
		Vertex two1 = db.addVertex(null);
		Vertex two2 = db.addVertex(null);
		db.addEdge(null, one1, two1,"one1-two1");
		db.addEdge(null, one1, two2,"one1-two2");
		db.addEdge(null, one2, two1,"one2-two1");
		db.addEdge(null, one2, two2,"one2-two2");
		
		assertEquals(4, countVertices());
		assertEquals(4, countEdges());		
		
		Iterable<Edge> iter = two2.getInEdges("one2-two2");
		for ( Edge edge : iter ) {
			if ( edge.getOutVertex().getId().equals(one2.getId()) ) {
				db.removeEdge(edge);
			}
		}

	}
	
}
