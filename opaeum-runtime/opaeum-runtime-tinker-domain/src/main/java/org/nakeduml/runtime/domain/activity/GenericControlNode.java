package org.nakeduml.runtime.domain.activity;

import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class GenericControlNode extends ControlNode<Token, Token> {

	public GenericControlNode() {
		super();
	}

	public GenericControlNode(boolean persist, String name) {
		super(persist, name);
	}

	public GenericControlNode(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected abstract List<? extends ActivityEdge<? extends Token>> getInFlows();

}
