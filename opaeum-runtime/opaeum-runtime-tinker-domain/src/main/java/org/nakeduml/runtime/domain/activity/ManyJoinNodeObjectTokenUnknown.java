package org.nakeduml.runtime.domain.activity;

import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ManyJoinNodeObjectTokenUnknown extends JoinNodeObjectTokenUnknown<CollectionObjectToken<?>> {

	public ManyJoinNodeObjectTokenUnknown() {
		super();
	}
	
	public ManyJoinNodeObjectTokenUnknown(Vertex vertex) {
		super(vertex);
	}	

	public ManyJoinNodeObjectTokenUnknown(boolean persist, String name) {
		super(persist, name);
	}
	
	@Override
	protected abstract ManyObjectFlowUnknown getOutFlow();

	@Override
	protected abstract List<ManyObjectFlowUnknown> getInFlows();
	
}
