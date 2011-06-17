package org.tinker.orientbug;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Assert;
import org.junit.Test;
import org.nakeduml.test.tinker.BaseLocalDbTest;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public class TestOrientEdgeDeletion extends BaseLocalDbTest {
	
	@Test
	public void testOrientBug() {
		Vertex one = db.addVertex(null);
		Vertex two = db.addVertex(null);
		Edge edge = db.addEdge(null, one, two,"one-two");
		edge.setProperty("inClass", two.getClass().getName());
		
		assertEquals(2, db.countVertices());
		assertEquals(1, db.countEdges());		
		
		Iterable<Edge> iter = one.getOutEdges("one-two");
		if ( iter.iterator().hasNext() ) {
			Edge edge1 = iter.iterator().next();
			System.out.println(edge1.getInVertex().toString());
			System.out.println(edge1.getOutVertex().toString());
			System.out.println(edge1.toString());
			System.out.println(edge1.getProperty("inClass"));
			db.removeEdge(edge1);
		}
		assertEquals(2, db.countVertices());
		assertEquals(0, db.countEdges());		
		
		Iterable<Edge> iter9 = one.getOutEdges("one-two");
		assertFalse(iter9.iterator().hasNext());
	}
	
	@Test
	public void testVertexRemoval() {
		Vertex one = db.addVertex(null);
		one.setProperty("testProperty", "dribble");
		db.removeVertex(one);
		Assert.assertEquals("dribble", one.getProperty("testProperty"));
	}
	
}
