package org.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.orientechnologies.orient.core.record.impl.ODocument;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientEdge;
import com.tinkerpop.blueprints.pgm.impls.orientdb.OrientVertex;

public class TinkerUtil {

	public static Set<Edge> getEdgesBetween(Vertex v1, Vertex v2, String... labels) {
		Set<Edge> result = new HashSet<Edge>();
		Set<ODocument> rawEdges = GraphDb.getRawGraph().getEdgesBetweenVertexes(getRawDocument(v1), getRawDocument(v2), labels);
		for (ODocument rawEdge : rawEdges) {
			result.add(new OrientEdge(GraphDb.getOrientGraph(), rawEdge));
		}
		return result;
	}

	public static ODocument getRawDocument(Vertex v1) {
		return ((OrientVertex) v1).getRawElement();
	}

	public static Collection convertEnumsForPersistence(Collection<? extends Enum> multiEmbeddedReason) {
		Collection<String> persistentCollection;
		if (multiEmbeddedReason instanceof Set) {
			persistentCollection = new HashSet<String>();
		} else {
			persistentCollection = new ArrayList<String>();
		}
		for (Enum e : multiEmbeddedReason) {
			persistentCollection.add(e.toString());
		}
		return persistentCollection;
	}

	public static Collection convertEnumsFromPersistence(Collection<String> multiEmbeddedReason, Class<? extends Enum> e, boolean isOrdered) {
		if (multiEmbeddedReason != null) {
			Collection<Enum> persistentCollection;
			if (!isOrdered) {
				persistentCollection = new HashSet<Enum>();
			} else {
				persistentCollection = new ArrayList<Enum>();
			}
			for (String s : multiEmbeddedReason) {
				persistentCollection.add(Enum.valueOf(e, s));
			}
			return persistentCollection;
		} else {
			return isOrdered ? new ArrayList(): new HashSet();
		}
	}

}
