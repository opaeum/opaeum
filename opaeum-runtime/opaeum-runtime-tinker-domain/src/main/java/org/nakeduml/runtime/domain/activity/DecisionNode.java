package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.pipes.util.SingleIterator;

public abstract class DecisionNode<T extends Token> extends ControlNode<T> {

	public DecisionNode() {
		super();
	}
	
	public DecisionNode(Vertex vertex) {
		super(vertex);
	}	

	public DecisionNode(boolean persist, String name) {
		super(persist, name);
	}

	protected abstract ActivityEdge<T> getInFlow();

	@Override
	protected List<ActivityEdge<T>> getInFlows() {
		List<ActivityEdge<T>> result = new ArrayList<ActivityEdge<T>>();
		result.add(getInFlow());
		return result;
	}
	
	@Override
	protected Boolean executeNode() {
		List<Boolean> flowResult = new ArrayList<Boolean>();

		setNodeStatus(NodeStatus.ENABLED);
		setNodeStatus(NodeStatus.ACTIVE);

		execute();

		this.nodeStat.increment();

		boolean oneOutgoingFlowGuardSucceeded = false;
		for (T token : getInTokens()) {
			// For each out flow add a token
			for (ActivityEdge<T> flow : getOutFlows()) {
				if (flow.evaluateGuardConditions(token)) {
					oneOutgoingFlowGuardSucceeded = true;
					T duplicate = token.duplicate(flow.getName());
					addOutgoingToken(duplicate);
					flow.setStarts(new SingleIterator<T>(duplicate));
					// Continue each out flow with its tokens
					flowResult.add(flow.processNextStart());
					break;
				}
			}
			token.remove();
		}
		
		if (!oneOutgoingFlowGuardSucceeded) {
			throw new IllegalStateException("Model is ill formed, one guard must succeed for a decision node.");
		}

		//TODO Start transaction
		setNodeStatus(NodeStatus.COMPLETE);
		//TODO End transaction
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
