package org.nakeduml.runtime.domain.activity;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class JoinNode<T extends Token> extends ControlNode<T> {

	public JoinNode() {
		super();
	}
	
	public JoinNode(Vertex vertex) {
		super(vertex);
	}	

	public JoinNode(boolean persist, String name) {
		super(persist, name);
	}
	
	protected abstract ActivityEdge<T> getOutFlow();
	
}
