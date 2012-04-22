package org.nakeduml.runtime.domain.activity;

import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class OneDecisionObjectTokenKnown<O> extends DecisionObjectTokenKnown<O, SingleObjectToken<O>> {

	public OneDecisionObjectTokenKnown() {
		super();
	}

	public OneDecisionObjectTokenKnown(boolean persist, String name) {
		super(persist, name);
	}

	public OneDecisionObjectTokenKnown(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected abstract OneObjectFlowKnown<O> getInFlow();

	@Override
	protected abstract List<? extends OneObjectFlowKnown<O>> getOutFlows();

}
