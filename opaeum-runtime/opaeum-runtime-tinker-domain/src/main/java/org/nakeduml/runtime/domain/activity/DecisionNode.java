package org.nakeduml.runtime.domain.activity;

import java.util.ArrayList;
import java.util.List;

import org.nakeduml.tinker.runtime.GraphDb;

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
	protected boolean mayContinue() {
		return doAllIncomingFlowsHaveTokens();
	}	
	
	@Override
	protected Boolean executeNode() {
		List<Boolean> flowResult = new ArrayList<Boolean>();

		removeIncomingControlTokens();

		setNodeStatus(NodeStatus.ENABLED);
		setNodeStatus(NodeStatus.ACTIVE);

		execute();

		this.nodeStat.increment();
		
		boolean oneOutgoingFlowGuardSucceeded = false;
		//For each out control flow add a control token
		for (ActivityEdge<T> flow : getOutFlows()) {
			T controlToken = instantiateToken(flow.getName());
			if (flow.evaluateGuardConditions(controlToken)) {
				oneOutgoingFlowGuardSucceeded = true;
				addOutgoingToken(controlToken);
				flow.setStarts(new SingleIterator<T>(controlToken));
				//Continue each out flow with its tokens
				flowResult.add(flow.processNextStart());
				break;
			} else {
				GraphDb.getDb().removeVertex(controlToken.getVertex());
			}
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

	protected abstract T instantiateToken(String name);	

}
