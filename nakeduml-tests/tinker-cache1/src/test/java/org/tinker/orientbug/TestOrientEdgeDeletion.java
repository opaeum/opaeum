package org.tinker.orientbug;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;

public class TestOrientEdgeDeletion {
	
	private OrientGraph db;
	
	@Before
	public void before() {
		db = new OrientGraph("memory:test");
		db.clear();
	}

	@After
	public void after() {
		db.shutdown();
	}
	
	@Test
	public void testOrientBug() {
		Vertex one = db.addVertex(null);
		Vertex two = db.addVertex(null);
		Edge edge = db.addEdge(null, one, two,"one-two");
		edge.setProperty("inClass", two.getClass().getName());
		
		assertEquals(2, db.getRawGraph().countVertexes());
		assertEquals(1, db.getRawGraph().countEdges());		
		
		Iterable<Edge> iter = one.getOutEdges("one-two");
		if ( iter.iterator().hasNext() ) {
			Edge edge1 = iter.iterator().next();
			System.out.println(edge1.getInVertex().toString());
			System.out.println(edge1.getOutVertex().toString());
			System.out.println(edge1.toString());
			System.out.println(edge1.getProperty("inClass"));
			db.removeEdge(edge1);
		}
		assertEquals(2, db.getRawGraph().countVertexes());
		assertEquals(0, db.getRawGraph().countEdges());		
		
		Iterable<Edge> iter9 = one.getOutEdges("one-two");
		assertFalse(iter9.iterator().hasNext());
	}
	
}
