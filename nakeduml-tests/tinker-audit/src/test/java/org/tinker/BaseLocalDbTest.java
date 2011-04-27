package org.tinker;
import org.junit.After;
import org.junit.Before;
import org.util.DbListener;
import org.util.GraphDb;

import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;


public class BaseLocalDbTest {

	protected OrientGraph db;
	
	@Before
	public void before() {
		db = new OrientGraph("local:/tmp/orientdbtest1");
		db.setTransactionMode(Mode.MANUAL);
		db.clear();
		db.getRawGraph().registerListener(new DbListener());
		GraphDb.setDB(db);
		db.startTransaction();
		db.getRawGraph().setRoot("root",db.getRawGraph().newInstance());
		GraphDb.getRoot().setProperty("transactionCount", 1);
		db.stopTransaction(Conclusion.SUCCESS);		
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
