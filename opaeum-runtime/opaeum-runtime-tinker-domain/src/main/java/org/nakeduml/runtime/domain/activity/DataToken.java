package org.nakeduml.runtime.domain.activity;

import org.nakeduml.tinker.runtime.GraphDb;
import org.opaeum.runtime.domain.IntrospectionUtil;

import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Vertex;

public class DataToken extends Token {

	public DataToken(String edgeName) {
		super(edgeName);
	}

	public DataToken(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected void addEdgeToActivityNode(ActivityNode<? extends Token> node) {
		Edge edge = GraphDb.getDb().addEdge(null, node.vertex, getVertex(), TOKEN + getEdgeName());
		edge.setProperty("outClass", IntrospectionUtil.getOriginalClass(node.getClass()).getName());
	}
	
	public Object getData() {
		return null;
	}

}
