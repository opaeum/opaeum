package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class JoinNodeObjectTokenUnknown<IN extends ObjectToken<?>> extends JoinNode<IN, IN> {

	public JoinNodeObjectTokenUnknown() {
		super();
	}
	
	public JoinNodeObjectTokenUnknown(Vertex vertex) {
		super(vertex);
	}	

	public JoinNodeObjectTokenUnknown(boolean persist, String name) {
		super(persist, name);
	}
	
	@Override
	protected abstract ObjectFlowUnknown<IN> getOutFlow();

	@Override
	protected abstract List<? extends ObjectFlowUnknown<IN>> getInFlows();

	@Override
	protected List<ObjectFlowUnknown<IN>> getOutFlows() {
		List<ObjectFlowUnknown<IN>> result = new ArrayList<ObjectFlowUnknown<IN>>();
		result.add(getOutFlow());
		return result;
	}
	
}
