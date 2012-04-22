package org.nakeduml.runtime.domain.activity;

import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;


public abstract class OneMergeNodeObjectTokenUnknown extends MergeNodeObjectTokenUnknown<SingleObjectToken<?>,SingleObjectToken<?>> {

	public OneMergeNodeObjectTokenUnknown() {
		super();
	}

	public OneMergeNodeObjectTokenUnknown(boolean persist, String name) {
		super(persist, name);
	}

	public OneMergeNodeObjectTokenUnknown(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected abstract OneObjectFlowUnknown getOutFlow();

	@Override
	protected abstract List<? extends OneObjectFlowUnknown> getInFlows();

}
