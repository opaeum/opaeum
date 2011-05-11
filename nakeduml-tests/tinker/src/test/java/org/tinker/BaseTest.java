package org.tinker;

import org.junit.After;
import org.junit.Before;
import org.util.GraphDb;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.TransactionalGraph;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.dex.DexGraph;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;
import com.tinkerpop.blueprints.pgm.impls.sail.impls.MemoryStoreSailGraph;
import com.tinkerpop.blueprints.pgm.impls.tg.TinkerGraph;

public class BaseTest {

	protected TransactionalGraph db;
	private GraphEnum graphType = GraphEnum.TINKER;

	@Before
	public void before() {
		switch (graphType) {
		case NEO4J:
			db = new Neo4jGraph("memory:test");
			break;
		case ORIENT:
			db = new OrientGraph("memory:test");
			break;
//		case TINKER:
//			db = new TinkerGraph();
//			break;
//		case SAIL:
//			db = new MemoryStoreSailGraph();
//			break;
//		case DEX:
//			db = new DexGraph("asd");
//			break;
		default:
			throw new IllegalStateException();
		}
		db.clear();
		GraphDb.setDB(db);
	}

	@After
	public void after() {
		db.shutdown();
		GraphDb.setDB(null);
	}

	protected long countVertices() {
		switch (graphType) {
		case ORIENT:
			return ((OrientGraph)db).getRawGraph().countVertexes();
		default:
			int count = 0;
			Iterable<Vertex> vertices = db.getVertices();
			for (@SuppressWarnings("unused") Vertex vertex : vertices) {
				count++;
			}
			return count;
		}		
	}

	protected long countEdges() {
		switch (graphType) {
		case ORIENT:
			return ((OrientGraph)db).getRawGraph().countEdges();
		default:
			int count = 0;
			Iterable<Edge> edges = db.getEdges();
			for (@SuppressWarnings("unused") Edge edge : edges) {
				count++;
			}
			return count;
		}
	}
	
	private enum GraphEnum {
		ORIENT, NEO4J, TINKER, SAIL, DEX;
	}

}
