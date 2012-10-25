package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class MergeNode<T extends Token> extends ControlNode<T> {

	public MergeNode() {
		super();
	}
	
	public MergeNode(Vertex vertex) {
		super(vertex);
	}		

	public MergeNode(boolean persist, String name) {
		super(persist, name);
	}
	
	protected abstract ActivityEdge<T> getOutFlow();

	@Override
	protected List<? extends ActivityEdge<T>> getOutFlows() {
		List<ActivityEdge<T>> result = new ArrayList<ActivityEdge<T>>();
		result.add(getOutFlow());
		return result;
	}	

	@Override
	protected boolean doAllIncomingFlowsHaveTokens() {
		return true;
	}
}
