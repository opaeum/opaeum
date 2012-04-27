package org.nakeduml.runtime.domain.activity;

import java.util.Collection;

import org.nakeduml.tinker.runtime.GraphDb;
import org.opaeum.runtime.domain.IntrospectionUtil;

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
		edge.setProperty("outClass", IntrospectionUtil.getOriginalClass(node.getClass()).getName());
	}

	public abstract void remove();
	
	public abstract Object getObject();
	
	public abstract int getNumberOfElements();
	
	public abstract Collection<O> getElements();

}
