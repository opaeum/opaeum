package org.tinker;
import org.junit.After;
import org.junit.Before;
import org.util.DbThreadVar;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;


public class BaseTest {

	protected OrientGraph db;
	
	@Before
	public void before() {
		db = new OrientGraph("memory:test");
		db.clear();
		DbThreadVar.setDB(db);
	}

	@After
	public void after() {
		db.shutdown();
		DbThreadVar.setDB(null);
	}
	
	protected int countVertices() {
		int result = 0;
		Iterable<Vertex> iter = db.getVertices();
		for (@SuppressWarnings("unused") Vertex vertex : iter) {
			result++;
		}
		return result;
	}
	protected int countEdges() {
		int result = 0;
		Iterable<Edge> iter = db.getEdges();
		for (@SuppressWarnings("unused") Edge edge : iter) {
			result++;
		}
		return result;
	}

}
