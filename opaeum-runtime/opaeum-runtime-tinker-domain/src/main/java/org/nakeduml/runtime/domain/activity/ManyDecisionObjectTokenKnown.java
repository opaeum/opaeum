package org.nakeduml.runtime.domain.activity;

import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ManyDecisionObjectTokenKnown<O> extends DecisionObjectTokenKnown<O, CollectionObjectToken<O>> {

	public ManyDecisionObjectTokenKnown() {
		super();
	}

	public ManyDecisionObjectTokenKnown(boolean persist, String name) {
		super(persist, name);
	}

	public ManyDecisionObjectTokenKnown(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected abstract ManyObjectFlowKnown<O> getInFlow();

	@Override
	protected abstract List<? extends ManyObjectFlowKnown<O>> getOutFlows();

}
