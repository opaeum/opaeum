package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import org.nakeduml.runtime.domain.activity.interf.IActivityParameterNode;

import com.tinkerpop.blueprints.pgm.Vertex;

public abstract class ActivityParameterNode<O,OUT extends ObjectToken<O>> extends ObjectNode<O,OUT,OUT> implements IActivityParameterNode<O,OUT> {

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
	public boolean mayContinue() {
		return true;
	}
	
	@Override
	protected Boolean executeNode() {
		List<Boolean> flowResult = new ArrayList<Boolean>();

		setNodeStatus(NodeStatus.ENABLED);
		setNodeStatus(NodeStatus.ACTIVE);

		this.nodeStat.increment();

		for (OUT objectToken : getInTokens()) {
			// For each out flow add a token
			for (ObjectFlowKnown<O, OUT> flow : getOutgoing()) {
				OUT duplicate = objectToken.duplicate(flow.getName());
				addOutgoingToken(duplicate);
			}
			objectToken.remove();
		}
		// Continue each out flow with its tokens
		for (ObjectFlowKnown<O,OUT> flow : getOutgoing()) {
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
