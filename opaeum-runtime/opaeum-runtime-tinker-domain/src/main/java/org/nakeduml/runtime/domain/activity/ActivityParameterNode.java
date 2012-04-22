package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ActivityParameterNode<O, IN extends ObjectToken<O>, OUT extends ObjectToken<O>> extends ObjectNode<O,IN,OUT> {

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

		for (IN objectToken : getInTokens()) {
			// For each out flow add a token
			for (ObjectFlowKnown<O, OUT> flow : getOutFlows()) {
				@SuppressWarnings("unchecked")
				OUT duplicate = (OUT) objectToken.duplicate(flow.getName());
				addOutgoingToken(duplicate);
			}
			objectToken.remove();
		}
		// Continue each out flow with its tokens
		for (ObjectFlowKnown<O,OUT> flow : getOutFlows()) {
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
