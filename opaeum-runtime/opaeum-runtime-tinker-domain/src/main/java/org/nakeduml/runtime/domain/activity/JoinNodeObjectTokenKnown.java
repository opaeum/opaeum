package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class JoinNodeObjectTokenKnown<O, T extends ObjectToken<O>> extends JoinNode<T, T> {

	public JoinNodeObjectTokenKnown() {
		super();
	}

	public JoinNodeObjectTokenKnown(Vertex vertex) {
		super(vertex);
	}

	public JoinNodeObjectTokenKnown(boolean persist, String name) {
		super(persist, name);
	}

	@Override
	protected abstract ObjectFlowKnown<O, T> getOutFlow();

	@Override
	protected abstract List<? extends ObjectFlowKnown<O, T>> getInFlows();

	@Override
	protected List<ObjectFlowKnown<O, T>> getOutFlows() {
		List<ObjectFlowKnown<O, T>> result = new ArrayList<ObjectFlowKnown<O, T>>();
		result.add(getOutFlow());
		return result;
	}

}
