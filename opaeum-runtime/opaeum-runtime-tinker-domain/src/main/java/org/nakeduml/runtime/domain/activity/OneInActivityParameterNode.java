package org.nakeduml.runtime.domain.activity;

import java.util.Collections;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class OneInActivityParameterNode<O> extends InActivityParameterNode<O,SingleObjectToken<O>, SingleObjectToken<O>> {

	public OneInActivityParameterNode() {
		super();
	}

	public OneInActivityParameterNode(boolean persist, String name) {
		super(persist, name);
	}

	public OneInActivityParameterNode(Vertex vertex) {
		super(vertex);
	}
	
	@Override
	protected List<OneObjectFlowKnown<O>> getInFlows() {
		return Collections.emptyList();
	}

	@Override
	protected abstract List<OneObjectFlowKnown<O>> getOutFlows();

	@Override
	protected int countNumberOfElementsOnTokens() {
		return getInTokens().size();
	}	

}
