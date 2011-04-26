package org.tinker.tinkerbug;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;

public class TestTinkerEdgeDeletion {
	
	private Graph db;
	
	@Before
	public void before() {
		db = new Neo4jGraph("memory:test");
		db.clear();
	}

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
	
	protected long countVertices() {
		int count = 0;
		Iterable<Vertex> vertices = db.getVertices();
		for (@SuppressWarnings("unused") Vertex vertex : vertices) {
			count++;
		}
		return count;
	}

	protected long countEdges() {
		int count = 0;
		Iterable<Edge> edges = db.getEdges();
		for (@SuppressWarnings("unused") Edge edge : edges) {
			count++;
		}
		return count;
	}	
	
}
