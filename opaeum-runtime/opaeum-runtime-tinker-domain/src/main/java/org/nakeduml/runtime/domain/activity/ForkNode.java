package org.nakeduml.runtime.domain.activity;


import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ForkNode<T extends Token> extends ControlNode<T> {

	public ForkNode() {
		super();
	}
	
	public ForkNode(Vertex vertex) {
		super(vertex);
	}	

	public ForkNode(boolean persist, String name) {
		super(persist, name);
	}
	
	protected abstract ActivityEdge<T> getInFlow();

	@Override
	protected List<ActivityEdge<T>> getInFlows() {
		List<ActivityEdge<T>> result = new ArrayList<ActivityEdge<T>>();
		result.add(getInFlow());
		return result;
	}	
	
}
