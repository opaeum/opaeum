package org.tinker;

import org.junit.After;
import org.junit.Before;
import org.util.DbListener;
import org.util.GraphDb;
import org.util.Neo4jTransactionEventHandler;

import com.orientechnologies.orient.core.config.OGlobalConfiguration;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.TransactionalGraph;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Conclusion;
import com.tinkerpop.blueprints.pgm.TransactionalGraph.Mode;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;

public class BaseLocalDbTest {

	protected TransactionalGraph db;
	private GraphEnum graphType = GraphEnum.ORIENT;

	@Before
	public void before() {
		switch (graphType) {
		case NEO4J:
			db = new Neo4jGraph("local:/tmp/tinker-neo4j-audit");
			db.clear();
			db.setTransactionMode(Mode.MANUAL);
			((Neo4jGraph)db).getRawGraph().registerTransactionEventHandler(new Neo4jTransactionEventHandler<Object>());
			GraphDb.setDB(db);
			db.startTransaction();
			//This is for show, it makes Neo4j create the reference node
			((Neo4jGraph)db).getRawGraph().createNode();
			((Neo4jGraph)db).getRawGraph().getReferenceNode().setProperty("transactionCount", 1);
			db.stopTransaction(Conclusion.SUCCESS);
			break;
		case ORIENT:
			db = new OrientGraph("local:/tmp/tinker-orient-audit");
			db.clear();
			OGlobalConfiguration.TX_USE_LOG.setValue(false);
			db.setTransactionMode(Mode.MANUAL);
			((OrientGraph)db).getRawGraph().registerListener(new DbListener());
			GraphDb.setDB(db);
			db.startTransaction();
			ODocument root = ((OrientGraph)db).getRawGraph().newInstance();
			((OrientGraph)db).getRawGraph().setRoot("root", root);
			root.field("transactionCount", 1);
			db.stopTransaction(Conclusion.SUCCESS);
			break;
		default:
			throw new IllegalStateException();
		}
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
