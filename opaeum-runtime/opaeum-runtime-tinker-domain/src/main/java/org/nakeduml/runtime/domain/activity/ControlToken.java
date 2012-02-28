package org.nakeduml.runtime.domain.activity;

import org.nakeduml.tinker.runtime.GraphDb;

import com.tinkerpop.blueprints.pgm.Vertex;

public class ControlToken extends Token {

	public ControlToken(Vertex vertex) {
		super(vertex);
	}

	public ControlToken(String edgeName) {
		super(edgeName);
	}

	@Override
	protected void addEdgeToActivityNode(ActivityNode<? extends Token> node) {
		// Multiple tokens from the same incoming edge is merged
		if (!node.vertex.getOutEdges(TOKEN + getEdgeName()).iterator().hasNext()) {
			GraphDb.getDb().addEdge(null, node.vertex, getVertex(), TOKEN + getEdgeName());
		} else {
			// TODO write test case for this
			GraphDb.getDb().removeVertex(getVertex());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ControlToken duplicate(String flowName) {
		return new ControlToken(flowName);
	}

	@Override
	public void remove() {
		GraphDb.getDb().removeVertex(getVertex());
	}

}
