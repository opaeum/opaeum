package org.util;

import org.neo4j.graphdb.Node;

import com.orientechnologies.orient.core.db.graph.OGraphDatabase;
import com.tinkerpop.blueprints.pgm.Graph;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jVertex;
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
		} else if (getDB() instanceof Neo4jGraph) {
			return new Neo4jVertex(((Neo4jGraph)getDB()).getRawGraph().getReferenceNode(), (Neo4jGraph)getDB()); 
		} else {
			throw new IllegalArgumentException("graph of  type " + getDB().getClass().getName() + " not yet supported!");
		}
	}
	
	public static int getTransactionCount() {
		if (getDB() instanceof OrientGraph) {
			return (Integer) getRoot().getProperty("transactionCount");
		} else if (getDB() instanceof Neo4jGraph) {
			return (Integer)((Neo4jGraph)getDB()).getRawGraph().getReferenceNode().getProperty("transactionCount");
		} else {
			throw new IllegalArgumentException("graph of  type " + getDB().getClass().getName() + " not yet supported!");
		}
	}
	
	public static void incrementTransactionCount() {
		if (getDB() instanceof OrientGraph) {
			getRoot().setProperty("transactionCount", (Integer) getRoot().getProperty("transactionCount")+1);
		} else if (getDB() instanceof Neo4jGraph) {
			Node referenceNode = ((Neo4jGraph)getDB()).getRawGraph().getReferenceNode();
			referenceNode.setProperty("transactionCount", referenceNode.getProperty("transactionCount"));
		} else {
			throw new IllegalArgumentException("graph of  type " + getDB().getClass().getName() + " not yet supported!");
		}
	}
	
	public static OrientGraph getOrientGraph() {
		return (OrientGraph)getDB();
	}
	
	public static OGraphDatabase getRawGraph() {
		return getOrientGraph().getRawGraph();
	}
	

}
