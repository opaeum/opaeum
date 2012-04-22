package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class OneOutActivityParameterNode<O> extends OutActivityParameterNode<O,SingleObjectToken<O>, SingleObjectToken<O>> {

	public OneOutActivityParameterNode() {
		super();
	}

	public OneOutActivityParameterNode(boolean persist, String name) {
		super(persist, name);
	}

	public OneOutActivityParameterNode(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected abstract List<OneObjectFlowKnown<O>> getInFlows();

	@Override
	protected List<OneObjectFlowKnown<O>> getOutFlows() {
		return Collections.emptyList();
	}	
	
	@Override
	public List<O> getReturnParameterValues() {
		List<O> result = new ArrayList<O>();
		for (SingleObjectToken<O> token : getInTokens()) {
			result.add(token.getObject());
		}
		return result;
	}

	@Override
	protected int countNumberOfElementsOnTokens() {
		return getInTokens().size();
	}	

}
