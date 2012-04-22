package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class DecisionObjectTokenKnown<O,IN extends ObjectToken<O>> extends DecisionNode<IN> {

	public DecisionObjectTokenKnown() {
		super();
	}

	public DecisionObjectTokenKnown(boolean persist, String name) {
		super(persist, name);
	}

	public DecisionObjectTokenKnown(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected abstract ObjectFlowKnown<O,IN> getInFlow();

	@Override
	protected List<ObjectFlowKnown<O,IN>> getInFlows() {
		List<ObjectFlowKnown<O,IN>> result = new ArrayList<ObjectFlowKnown<O,IN>>();
		result.add(getInFlow());
		return result;
	}
	
	@Override
	protected abstract List<? extends ObjectFlowKnown<O,IN>> getOutFlows();

}
