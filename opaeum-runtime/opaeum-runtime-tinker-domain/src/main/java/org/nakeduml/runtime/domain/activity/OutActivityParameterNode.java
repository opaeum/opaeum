package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class OutActivityParameterNode<O, IN extends ObjectToken<O>, OUT extends ObjectToken<O>> extends ActivityParameterNode<O, IN, OUT> {

	public OutActivityParameterNode() {
		super();
	}

	public OutActivityParameterNode(boolean persist, String name) {
		super(persist, name);
	}

	public OutActivityParameterNode(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected List<? extends ObjectFlowKnown<O, OUT>> getOutFlows() {
		return Collections.emptyList();
	}
	
	@Override
	protected Boolean executeNode() {
		List<Boolean> flowResult = new ArrayList<Boolean>();

		setNodeStatus(NodeStatus.ENABLED);
		setNodeStatus(NodeStatus.ACTIVE);

		this.nodeStat.increment();

		setNodeStatus(NodeStatus.COMPLETE);
		boolean result = true;
		for (Boolean b : flowResult) {
			if (!b) {
				result = false;
				break;
			}
		}
		return result;
	}

	public abstract List<O> getReturnParameterValues();
}
