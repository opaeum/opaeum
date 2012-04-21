package org.nakeduml.runtime.domain.activity;

import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ObjectToken<O> extends Token {

	public ObjectToken(Vertex vertex) {
		super(vertex);
	}

	public ObjectToken(String edgeName) {
		super(edgeName);
	}

	public ObjectToken(String edgeName, O object) {
		super(edgeName);
	}

	@Override
	protected void addEdgeToActivityNode(ActivityNode<? extends Token, ? extends Token> node) {
		Edge edge = GraphDb.getDb().addEdge(null, node.vertex, getVertex(), TOKEN + getEdgeName());
		edge.setProperty("tokenClass", getClass().getName());
	}

	@SuppressWarnings("unchecked")
	public abstract ObjectToken<O> duplicate(String flowName);

	public abstract void remove();

}
