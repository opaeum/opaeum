package org.nakeduml.tinker.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import com.tinkerpop.pipes.SingleIterator;

public abstract class AbstractDecisionNode extends AbstractAction {

	@Override
	protected boolean hasPostConditionPassed() {
		return true;
	}

	@Override
	protected boolean hasPreConditionPassed() {
		return true;
	}
	
	protected abstract AbstractControlFlowEdge getInControlFlow();

	@Override
	protected List<? extends AbstractControlFlowEdge> getInControlFlows() {
		return Arrays.asList(getInControlFlow());
	}
	
	@Override
	protected Boolean processNextStart() throws NoSuchElementException {
		//Persist incoming control tokens
		while (starts.hasNext()) {
			ControlToken incomingControlToken = (ControlToken) starts.next();
			//This also removes the token from the source
			addIncomingControlToken(incomingControlToken);
		}
		List<Boolean> flowResult = new ArrayList<Boolean>();
		if (doAllIncomingFlowsHaveTokens() && hasPreConditionPassed() && hasPostConditionPassed()) {
			
			removeIncomingControlTokens();
			
			setNodeStatus(NodeStatus.ENABLED);
			setNodeStatus(NodeStatus.ACTIVE);

			//Execute whatever
			
			//For each out control flow add a control token
			for (AbstractControlFlowEdge flow : getOutControlFlows()) {
				ControlToken controlToken = new ControlToken(flow.getName());
				if (flow.evaluateGuardConditions(controlToken)) {
					addOutgoingControlToken(controlToken);
					flow.setStarts(new SingleIterator<ControlToken>(controlToken));
					//Continue each out flow with its tokens
					flowResult.add(flow.processNextStart());
					break;
				}
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
		} else {
			return false;
		}
	}	


}
