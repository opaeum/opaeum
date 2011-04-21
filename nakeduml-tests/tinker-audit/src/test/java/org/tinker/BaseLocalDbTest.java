package org.tinker;
import org.junit.After;
import org.junit.Before;
import org.util.DbListener;
import org.util.GraphDb;
import org.util.NakedORecordHook;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;


public class BaseLocalDbTest {

	protected OrientGraph db;
	
	@Before
	public void before() {
		db = new OrientGraph("local:/tmp/orientdbtest2");
		db.setTransactionMode(Mode.MANUAL);
		db.clear();
//		db.getRawGraph().registerListener(new DbListener());
		db.getRawGraph().registerHook(new NakedORecordHook());
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
