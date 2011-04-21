package org.util;

import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientGraph;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientVertex;

public class GraphDb {

	private GraphDb() {
	}

	private static ThreadLocal<Graph> dbVar = new ThreadLocal<Graph>() {
		Graph db = null;

		public Graph get() {
			return db;
		}

		public void set(Graph newValue) {
			db = newValue;
		}

	};

	public static Graph getDB() {
		return dbVar.get();
	}
	
	public static void setDB(Graph db) {
		dbVar.set(db);
	}
	
	public static Vertex getRoot() {
		if (getDB() instanceof OrientGraph) {
			OrientGraph orientGraph = (OrientGraph) getDB();
			return new OrientVertex(orientGraph, orientGraph.getRawGraph().getRoot("root"));
		} else {
			throw new IllegalArgumentException("graph of  type " + getDB().getClass().getName() + " not yet supported!");
		}
	}
	
	public static int getTransactionCount() {
		Integer transactionCount = (Integer) getRoot().getProperty("transactionCount");
		return transactionCount;
	}
	
	public static void incrementTransactionCount() {
		getRoot().setProperty("transactionCount", (Integer) getRoot().getProperty("transactionCount")+1);
	}

}
