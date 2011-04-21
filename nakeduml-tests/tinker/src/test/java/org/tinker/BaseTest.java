package org.tinker;
import org.junit.After;
import org.junit.Before;
import org.util.GraphDb;

import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;


public class BaseTest {

	protected OrientGraph db;
	
	@Before
	public void before() {
		db = new OrientGraph("memory:test");
		db.clear();
		GraphDb.setDB(db);
	}

	@After
	public void after() {
		db.shutdown();
		GraphDb.setDB(null);
	}
	
	protected long countVertices() {
		return db.getRawGraph().countVertexes();
	}
	protected long countEdges() {
		return db.getRawGraph().countEdges();
	}

}
