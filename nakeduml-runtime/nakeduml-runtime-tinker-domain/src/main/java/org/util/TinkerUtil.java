package org.util;

import java.util.HashSet;
import java.util.Set;

import com.orientechnologies.orient.core.record.impl.ODocument;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientEdge;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientVertex;

public class TinkerUtil {

	public static Set<Edge> getEdgesBetween(Vertex v1, Vertex v2, String ... labels) {
		Set<Edge> result = new HashSet<Edge>();
		Set<ODocument> rawEdges = GraphDb.getRawGraph().getEdgesBetweenVertexes(getRawDocument(v1), getRawDocument(v2), labels);
		for (ODocument rawEdge : rawEdges) {
			result.add(new OrientEdge(GraphDb.getOrientGraph(), rawEdge));
		}
		return result;
	}
	
	public static ODocument getRawDocument(Vertex v1) {
		return ((OrientVertex)v1).getRawElement();
	}
	
}
