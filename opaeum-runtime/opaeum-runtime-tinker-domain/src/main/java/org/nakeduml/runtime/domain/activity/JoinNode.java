package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

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

	protected List<? extends ActivityEdge<T>> getOutFlows() {
		List<ActivityEdge<T>> result = new ArrayList<ActivityEdge<T>>();
		result.add(getOutFlow());
		return result;
	}
	
}
