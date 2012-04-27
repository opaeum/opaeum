package org.nakeduml.runtime.domain.activity;

import java.util.Collections;
import java.util.List;

import org.nakeduml.runtime.domain.activity.interf.IManyInActivityParameterNode;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ManyInActivityParameterNode<O> extends InActivityParameterNode<O,CollectionObjectToken<O>> implements IManyInActivityParameterNode<O> {

	public ManyInActivityParameterNode() {
		super();
	}

	public ManyInActivityParameterNode(boolean persist, String name) {
		super(persist, name);
	}

	public ManyInActivityParameterNode(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected List<ManyObjectFlowKnown<O>> getInFlows() {
		return Collections.emptyList();
	}

	@Override
	protected abstract List<ManyObjectFlowKnown<O>> getOutFlows();

}
