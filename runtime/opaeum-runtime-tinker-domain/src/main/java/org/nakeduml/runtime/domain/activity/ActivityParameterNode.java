package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ActivityParameterNode<O> extends ObjectNode<O> {

	public ActivityParameterNode() {
		super();
	}

	public ActivityParameterNode(boolean persist, String name) {
		super(persist, name);
	}

	public ActivityParameterNode(Vertex vertex) {
		super(vertex);
	}

	@Override
	protected boolean mayContinue() {
		return true;
	}

	@Override
	protected Boolean executeNode() {
		List<Boolean> flowResult = new ArrayList<Boolean>();

		setNodeStatus(NodeStatus.ENABLED);
		setNodeStatus(NodeStatus.ACTIVE);

		this.nodeStat.increment();

		for (ObjectToken<O> objectToken : getInTokens()) {
			// For each out flow add a token
			for (ActivityEdge<ObjectToken<O>> flow : getOutFlows()) {
				ObjectToken<O> duplicate = objectToken.duplicate(flow.getName());
				addOutgoingToken(duplicate);
			}
			objectToken.remove();
		}
		// Continue each out flow with its tokens
		for (ActivityEdge<ObjectToken<O>> flow : getOutFlows()) {
			flow.setStarts(getOutTokens(flow.getName()));
			flowResult.add(flow.processNextStart());
		}

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

}
