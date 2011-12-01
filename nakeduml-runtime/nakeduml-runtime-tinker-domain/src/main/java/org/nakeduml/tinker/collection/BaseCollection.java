package org.nakeduml.tinker.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.nakeduml.runtime.domain.CompositionNode;
import org.nakeduml.runtime.domain.TinkerCompositionNode;
import org.nakeduml.tinker.runtime.GraphDb;
import org.nakeduml.tinker.runtime.TransactionThreadEntityVar;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class BaseCollection<E> {

	protected boolean composite;
	protected boolean inverse;
	protected boolean manyToMany;
	protected boolean loaded = false;
	protected TinkerCompositionNode owner;
	protected Vertex vertex;
	protected String label;
	protected Class<?> parentClass;
	protected Map<Object, Vertex> internalVertexMap = new HashMap<Object, Vertex>();

	protected Edge addInternal(E e) {
		Vertex v;
		if (e instanceof TinkerCompositionNode) {
			TinkerCompositionNode node = (TinkerCompositionNode) e;
			TransactionThreadEntityVar.setNewEntity((TinkerCompositionNode) node);
			v = node.getVertex();
		} else if (e.getClass().isEnum()) {
			v = GraphDb.getDb().addVertex(null);
			v.setProperty("value", ((Enum<?>) e).name());
			this.internalVertexMap.put(((Enum<?>) e).name(), v);
		} else {
			v = GraphDb.getDb().addVertex(null);
			v.setProperty("value", e);
			this.internalVertexMap.put(e, v);
		}
		
		Edge edge = null;
		//See if edge already added, this can only happen with a manyToMany
		if (this.manyToMany) {
			Set<Edge> edgesBetween = GraphDb.getDb().getEdgesBetween(this.vertex, v, this.label);
			//Only a sequence can have duplicates
			if (this instanceof TinkerSequence) {
				for (Edge edgeBetween : edgesBetween) {
					if (edgeBetween.getProperty("vertexMatch").equals(this.vertex.getId().toString()+v.getId().toString())) {
						edge = edgeBetween;
					}
				}
			} else {
				if (!edgesBetween.isEmpty()) {
					if (edgesBetween.size()!=1) {
						throw new IllegalStateException("A set can only have one edge between the two ends");
					}
					edge = edgesBetween.iterator().next();
				}
			}
		}
		if (edge == null) {
			if (this.inverse) {
				edge = GraphDb.getDb().addEdge(null, this.vertex, v, this.label);
				edge.setProperty("outClass", this.parentClass.getName());
				edge.setProperty("inClass", e.getClass().getName());
				edge.setProperty("vertexMatch", this.vertex.getId().toString() + v.getId().toString());
			} else {
				edge = GraphDb.getDb().addEdge(null, v, this.vertex, this.label);
				edge.setProperty("outClass", e.getClass().getName());
				edge.setProperty("inClass", this.parentClass.getName());
				edge.setProperty("vertexMatch", v.getId().toString() + this.vertex.getId().toString());
			}
		}
		
		return edge;
	}
	
	protected void maybeCallInit(E e) {
		if (this.composite && e instanceof TinkerCompositionNode && !((TinkerCompositionNode) e).hasInitBeenCalled()) {
			((CompositionNode) e).init(this.owner);
		}
	}

	protected void maybeLoad() {
		if (!this.loaded) {
			loadFromVertex();
		}
	}

	protected abstract void loadFromVertex();

	protected Vertex getVertexForDirection(Edge edge) {
		if (this.inverse) {
			return edge.getInVertex();
		} else {
			return edge.getOutVertex();
		}
	}	
	
	protected Class<?> getClassToInstantiate(Edge edge) {
		try {
			if (this.inverse) {
				return Class.forName((String) edge.getProperty("inClass"));
			} else {
				return Class.forName((String) edge.getProperty("outClass"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
